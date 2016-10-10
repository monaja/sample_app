package com.brokersystems.invtransactions.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;

import com.brokersystems.invtransactions.model.QReceiptTrans;
import com.brokersystems.invtransactions.model.QTenantInvoice;
import com.brokersystems.invtransactions.model.QTransactions;
import com.brokersystems.invtransactions.model.ReceiptSettlementDetails;
import com.brokersystems.invtransactions.model.ReceiptTrans;
import com.brokersystems.invtransactions.model.ReceiptTransDtls;
import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.model.TenantInvoiceDetails;
import com.brokersystems.invtransactions.model.Transactions;
import com.brokersystems.invtransactions.repository.InvoiceDetailsRepository;
import com.brokersystems.invtransactions.repository.InvoiceRepository;
import com.brokersystems.invtransactions.repository.ReceiptDetailsRepository;
import com.brokersystems.invtransactions.repository.ReceiptRepository;
import com.brokersystems.invtransactions.repository.SettlementRepo;
import com.brokersystems.invtransactions.repository.TransactionRepository;
import com.brokersystems.invtransactions.service.ReceiptService;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.server.utils.FormatUtils;
import com.brokersystems.server.utils.NumberToWordsUtils;
import com.brokersystems.server.utils.UserUtils;
import com.brokersystems.setup.repository.CurrencyRepository;
import com.brokersystems.setup.repository.PaymentModeRepo;
import com.brokersystems.setup.repository.SequenceRepository;
import com.brokersystems.setup.repository.TenantRepository;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.QSystemSequence;
import com.brokersystems.setups.model.QTenantDef;
import com.brokersystems.setups.model.SystemSequence;
import com.mysema.query.types.Predicate;

@Service
public class ReceiptServiceImpl implements ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepo;

	@Autowired
	private TransactionRepository transRepo;

	@Autowired
	private ReceiptDetailsRepository rctDetailsRepo;

	@Autowired
	private CurrencyRepository currencyRepo;

	@Autowired
	private PaymentModeRepo paymentModeRepo;

	@Autowired
	private SequenceRepository sequenceRepo;

	@Autowired
	private UserUtils userUtils;

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private InvoiceDetailsRepository invoceDetRepo;

	@Autowired
	private SettlementRepo settlementRepo;

	@Override
	public DataTablesResult<ReceiptTrans> findAllReceipts(DataTablesRequest request) throws IllegalAccessException {
		Page<ReceiptTrans> page = receiptRepo.findAll(request.searchPredicate(QReceiptTrans.receiptTrans), request);
		return new DataTablesResult(request, page);
	}

	@Override
	public DataTablesResult<Transactions> findReceiptTransactions(DataTablesRequest request, String tenantFname,
			String otherName, String invoiceNumber) throws IllegalAccessException {
		QTenantDef tenant = QTransactions.transactions.tenant;
		Predicate pred = QTransactions.transactions.transDC.eq("D");// .and(tenant.fname.startsWithIgnoreCase(tenantFname).or(tenant.otherNames.startsWithIgnoreCase(otherName)).or(QTransactions.transactions.refno.eq(invoiceNumber)));
		Page<Transactions> page = transRepo.findAll(pred, request);
		return new DataTablesResult(request, page);
	}

	@Override
	@Modifying
	@Transactional(readOnly = false, rollbackFor = { BadRequestException.class })
	public Long createReceipt(ReceiptTrans receipt) throws BadRequestException {

		if (receipt.getCurrCode() == null) {
			throw new BadRequestException("Currency is mandatory");
		}
		if (receipt.getPayId() == null) {
			throw new BadRequestException("Payment Mode is mandatory");
		}

		Predicate seqPredicate = QSystemSequence.systemSequence.transType.eq("R");
		if (sequenceRepo.count(seqPredicate) == 0)
			throw new BadRequestException("Sequence for Receipt Transactions has not been setup");
		SystemSequence sequence = sequenceRepo.findOne(seqPredicate);
		Long seqNumber = sequence.getNextNumber();
		final String receiptNo = sequence.getSeqPrefix() + String.format("%05d", seqNumber);
		receipt.setReceiptNo(receiptNo);
		receipt.setCounter(BigInteger.valueOf(seqNumber));
		Currencies currency = currencyRepo.findOne(receipt.getCurrCode());
		List<ReceiptTransDtls> transDtls = new ArrayList<>();
		List<Transactions> transactions = new ArrayList<>();
		BigDecimal totalAllocAmount = BigDecimal.ZERO;
		for (ReceiptTransDtls tran : receipt.getDetails()) {
			Transactions transaction = transRepo.findOne(tran.getInvoiceCode());
			BigDecimal allocationAmt = tran.getRctAmount();
			BigDecimal balance = transaction.getTransBalance();
			BigDecimal rem = balance.subtract(allocationAmt);
			BigDecimal overpayment = BigDecimal.ZERO;
			if (rem.compareTo(BigDecimal.ZERO) == -1) {
				overpayment = rem;
				rem = BigDecimal.ZERO;

			}
			transaction.setTransBalance(rem.abs());
			if (rem.compareTo(BigDecimal.ZERO) == -1 || rem.compareTo(BigDecimal.ZERO) == 0) {
				transaction.setTransSettledAmt(balance);
			} else if (rem.compareTo(BigDecimal.ZERO) == 1) {
				transaction.setTransSettledAmt(rem);
			}
			tran.setReceipt(receipt);
			tran.setInvoice(transaction.getInvoice());
			tran.setInvoiceRevision(transaction.getInvoice().getRevisionNumber());
			tran.setRctType("INV");
			tran.setRctDC("C");

			Transactions transact = new Transactions();
			tran.setAllocated("Y");

			BigDecimal receiptAmt = BigDecimal.ZERO;
			overpayment = overpayment.abs();
			Date wefDate = transaction.getInvoice().getRenewalDate();
			Date wetDate = FormatUtils.addDays(FormatUtils.addMonths(transaction.getInvoice().getRenewalDate(),
					FormatUtils.calculateFrequencyRate(transaction.getInvoice().getFrequency())), -1);

			if (overpayment.compareTo(BigDecimal.ZERO) == 0) {
				createRenewal(transaction.getTenant().getTenId(), wefDate, wetDate);
			} else if (overpayment.compareTo(BigDecimal.ZERO) == 1) {
				receiptAmt = overpayment;
                BigDecimal remainder = receiptAmt.remainder(transaction.getInvoice().getInstallmentAmount());
				while (receiptAmt.compareTo(BigDecimal.ZERO) == 1) {
					BigDecimal installAmt = transaction.getInvoice().getInstallmentAmount();
					if (installAmt.compareTo(receiptAmt) == 1)
						installAmt = receiptAmt;
					createRenewal(transaction.getTenant().getTenId(), wefDate, wetDate);
					authorizeRenewal(transaction.getTenant().getTenId(), installAmt, transact);
					receiptAmt = receiptAmt.subtract(installAmt);
					wefDate = FormatUtils.addMonths(wefDate,
							FormatUtils.calculateFrequencyRate(transaction.getInvoice().getFrequency()));
					wetDate = FormatUtils
							.addDays(
									FormatUtils.addMonths(wefDate,
											FormatUtils
													.calculateFrequencyRate(transaction.getInvoice().getFrequency())),
									-1);
				}
				if (remainder.compareTo(BigDecimal.ZERO) == 0) {
					createRenewal(transaction.getTenant().getTenId(), wefDate, wetDate);
				}

			}
			 
			transact.setAuthoriedBy(userUtils.getCurrentUser().getUsername());
			transact.setAuthorized("Y");
			transact.setRefno(receiptNo);
			transact.setTenant(transaction.getTenant());
			transact.setTransAmount(tran.getRctAmount().abs());
			transact.setTransBalance(receiptAmt);
			transact.setTransCommission(BigDecimal.ZERO);
			transact.setTransCurrency(currency);
			transact.setTransDC("C");
			transact.setTransNetAmt(tran.getRctAmount().abs());
			transact.setTransTaxes(BigDecimal.ZERO);
			transact.setTranstype("RCT");
		    transact.setTransSettledAmt(tran.getRctAmount());
			transact.setTransDate(new Date());
			createSettlements(transaction.getInvoice(), transaction, transact);
			transactions.add(transact);
			transactions.add(transaction);
			transDtls.add(tran);
			totalAllocAmount = totalAllocAmount.add(tran.getRctAmount());
		}
		if (totalAllocAmount.compareTo(receipt.getReceiptAmount()) != 0) {
			throw new BadRequestException("Total Allocation Amount " + totalAllocAmount + " and Receipt amount "
					+ receipt.getReceiptAmount() + " doesnt tally....");
		}
		rctDetailsRepo.save(transDtls);
		transRepo.save(transactions);
		receipt.setTransCurrency(currency);
		receipt.setPaymentMode(paymentModeRepo.findOne(receipt.getPayId()));
		receipt.setReceiptUser(userUtils.getCurrentUser());
		receipt.setReceiptTransDate(new Date());
		sequence.setLastNumber(seqNumber);
		sequence.setNextNumber(seqNumber + 1);
		sequenceRepo.save(sequence);
		Float amount = receipt.getReceiptAmount().floatValue();
		int figure = (int) Math.floor(amount);
		int cent = (int) Math.floor((amount - figure) * 100.0f);
		String words = "";
		if (cent > 0) {
			words = NumberToWordsUtils.convert(figure) + " and " + NumberToWordsUtils.convert(cent) + " cents";
		} else {
			words = NumberToWordsUtils.convert(figure);
		}
		receipt.setAmountWords(words);

		ReceiptTrans trans = receiptRepo.save(receipt);
		return trans.getReceiptId();

	}

	@Override
	@Modifying
	@Transactional(readOnly = false, rollbackFor = { BadRequestException.class }, propagation = Propagation.REQUIRED)
	public void createRenewal(Long tenId, Date wefDate, Date wetDate) throws BadRequestException {

		Predicate pred = QTenantInvoice.tenantInvoice.tenant.tenId.eq(tenId)
				.and(QTenantInvoice.tenantInvoice.currentStatus.eq("A"));
		if (invoiceRepo.count(pred) > 1) {
			throw new BadRequestException(
					"Cannot create a renewal for this tenant...More than one active transaction exist...");
		}
		TenantInvoice invoice = invoiceRepo.findOne(pred);
		Long count = invoiceRepo.count(QTenantInvoice.tenantInvoice.invoiceNumber.eq(invoice.getInvoiceNumber()));
		TenantInvoice renewal = new TenantInvoice();
		List<TenantInvoiceDetails> details = invoice.getInvDetails();
		List<TenantInvoiceDetails> renDetails = new ArrayList<>();
		BigDecimal grossAmount = BigDecimal.ZERO;
		BigDecimal taxAmount = BigDecimal.ZERO;
		BigDecimal netAmount = BigDecimal.ZERO;
		for (TenantInvoiceDetails inv : details) {
			if (inv.getCharge().getFrequency().equalsIgnoreCase("Monthly")) {
				TenantInvoiceDetails det = new TenantInvoiceDetails();
				grossAmount = grossAmount.add(inv.getAmount());
				netAmount = netAmount.add(inv.getNetAmount());
				det.setAmount(inv.getAmount());
				det.setCharge(inv.getCharge());
				det.setInvoice(renewal);
				det.setNetAmount(inv.getNetAmount());
				det.setRateType(inv.getRateType());
				renDetails.add(det);

			}
		}
		taxAmount = grossAmount.subtract(netAmount);
		invoceDetRepo.save(renDetails);
		renewal.setCurrentStatus("RN");
		renewal.setBranch(invoice.getBranch());
		renewal.setFrequency(invoice.getFrequency());
		renewal.setInstallmentAmount(invoice.getInstallmentAmount());
		renewal.setInvAmount(grossAmount);
		renewal.setInvoiceDate(new Date());
		renewal.setInvoiceNumber(invoice.getInvoiceNumber());
		renewal.setNetAmount(netAmount);
		renewal.setPaymentMode(invoice.getPaymentMode());
		renewal.setPreviousTrans(invoice);
		renewal.setStatus("R");
		renewal.setTaxAmount(taxAmount);
		renewal.setInvDetails(renDetails);
		renewal.setRevisionNumber(invoice.getInvoiceNumber() + "/" + (count + 1));
		// renewal.setStatus("RN");
		renewal.setRenewalDate(FormatUtils.addDays(wetDate, 1));
		renewal.setTenant(invoice.getTenant());
		renewal.setTransCurrency(invoice.getTransCurrency());
		renewal.setTransType("RN");
		renewal.setWefDate(wefDate);
		renewal.setWetDate(wetDate);
		invoiceRepo.save(renewal);
		Transactions trans = new Transactions();
		trans.setAuthoriedBy(userUtils.getCurrentUser().getUsername());
		trans.setAuthorized("R");
		trans.setRefno(invoice.getInvoiceNumber());
		trans.setTenant(invoice.getTenant());
		trans.setTransAmount(grossAmount);
		trans.setTransBalance(grossAmount);
		trans.setTransCommission(BigDecimal.ZERO);
		trans.setTransCurrency(invoice.getTransCurrency());
		trans.setTransDC("D");
		trans.setTransNetAmt(netAmount);
		trans.setTransTaxes(taxAmount);
		trans.setTranstype("RN");
		trans.setTransSettledAmt(BigDecimal.ZERO);
		trans.setTransDate(new Date());
		trans.setInvoice(invoice);
		transRepo.save(trans);
	}

	@Override
	@Modifying
	@Transactional(readOnly = false, rollbackFor = { BadRequestException.class }, propagation = Propagation.REQUIRED)
	public void authorizeRenewal(Long tenId, BigDecimal rctAmount, Transactions receipt) throws BadRequestException {
		Predicate renPred = QTenantInvoice.tenantInvoice.currentStatus.eq("RN")
				.and(QTenantInvoice.tenantInvoice.status.eq("R"));
		if (invoiceRepo.count(renPred) > 1) {
			throw new BadRequestException(
					"Cannot create a renewal for this tenant...More than one active transaction exist...");
		}

		if (invoiceRepo.count(renPred) == 0) {
			throw new BadRequestException("No Renewal Invoice to Authorize.....");
		}

		TenantInvoice latestRenewal = invoiceRepo.findOne(renPred);
		

		BigDecimal installAmt = latestRenewal.getInstallmentAmount();
		boolean authInvoice = false;
		if (rctAmount.compareTo(installAmt) == 0)
			authInvoice = true;
		if (authInvoice) {
			latestRenewal.setAuthBy(userUtils.getCurrentUser());
			latestRenewal.setStatus("A");
			invoiceRepo.save(latestRenewal);
		}

		Predicate renTran = QTransactions.transactions.transtype.eq("RN")
				.and(QTransactions.transactions.authorized.eq("R"));
		if (transRepo.count(renTran) > 1) {
			throw new BadRequestException("An error detected on Tenant Renewals Transactions");
		}

		if (transRepo.count(renTran) == 0) {
			throw new BadRequestException("No Renewal Transaction to Authorize");
		}

		Transactions transaction = transRepo.findOne(renTran);
		if (authInvoice) {
			transaction.setAuthoriedBy(userUtils.getCurrentUser().getUsername());
			transaction.setAuthorized("Y");
		     
		}
		transaction.setTransBalance(transaction.getTransBalance().subtract(rctAmount));
		transaction.setTransSettledAmt(rctAmount);
		Transactions trans = transRepo.save(transaction);
		createSettlements(latestRenewal, trans, receipt);
	}

	@Override
	@Modifying
	@Transactional(readOnly = false, rollbackFor = {
			BadRequestException.class }, propagation = Propagation.REQUIRES_NEW)
	public void createSettlements(TenantInvoice invoice, Transactions debit, Transactions credit)
			throws BadRequestException {
		ReceiptSettlementDetails settlement = new ReceiptSettlementDetails();
		settlement.setCredit(credit);
		settlement.setDebit(debit);
		settlement.setInvoice(invoice);
		settlementRepo.save(settlement);

	}

}
