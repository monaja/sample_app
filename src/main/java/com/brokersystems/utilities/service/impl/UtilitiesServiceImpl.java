package com.brokersystems.utilities.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import com.brokersystems.invtransactions.repository.ReceiptRepository;
import com.brokersystems.invtransactions.repository.SettlementRepo;
import com.brokersystems.invtransactions.repository.TransactionRepository;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.server.utils.FormatUtils;
import com.brokersystems.server.utils.UserUtils;
import com.brokersystems.setup.repository.CurrencyRepository;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.TenantDef;
import com.brokersystems.setups.service.OrganizationService;
import com.brokersystems.utilities.service.UtilitiesService;
import com.mysema.query.types.Predicate;

@Service
public class UtilitiesServiceImpl implements UtilitiesService {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private ReceiptRepository receiptRepo;

	@Autowired
	private TransactionRepository transRepo;

	@Autowired
	private UserUtils userUtils;


	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private InvoiceDetailsRepository invoceDetRepo;

	@Autowired
	private SettlementRepo settlementRepo;

	@Override
	public int getCurrencyFractionUnits() {
		Organization organization = organizationService.getOrganizationDetails();
		return organization.getCurrency().getFractionUnits();
	}

	@Override
	@Modifying
	@Transactional(readOnly = false, rollbackFor = { BadRequestException.class }, propagation = Propagation.REQUIRED)
	public void allocateReceipt(Long receiptId) throws BadRequestException {
		ReceiptTrans receipt = receiptRepo.findOne(receiptId);
		List<Transactions> transactions = new ArrayList<>();
		List<ReceiptTransDtls> rctDetails = receipt.getReceiptDtls();
		Currencies currency = receipt.getTransCurrency();
		for (ReceiptTransDtls tran : rctDetails) {
			TenantInvoice invoice = tran.getInvoice();
			Iterable<Transactions> trans = transRepo.findAll(QTransactions.transactions.invoice.eq(invoice)
					.and(QTransactions.transactions.transBalance.goe(BigDecimal.ZERO)).and(QTransactions.transactions.transDC.eq("D")));
			Transactions transaction = null;
			for (Transactions tr : trans) {
				if (tr.getTransBalance().compareTo(BigDecimal.ZERO) == 1) {
					transaction = tr;
					break;
				}
			}
			Predicate pendingRen = QTenantInvoice.tenantInvoice.currentStatus.eq("RN")
					.and(QTenantInvoice.tenantInvoice.status.eq("R"))
					.and(QTenantInvoice.tenantInvoice.invoiceNumber.eq(invoice.getInvoiceNumber()));
			
			if (invoiceRepo.count(pendingRen) > 1) {
				throw new BadRequestException(
						"Error doing auto renewals for the invoice...More than one active transaction exist...");
			}
			boolean authTrans = false;
			TenantInvoice ActiveRen = invoiceRepo.findOne(pendingRen);
			BigDecimal allocationAmt = tran.getRctAmount();
			BigDecimal balance = transaction.getTransBalance();
			BigDecimal rem = balance.subtract(allocationAmt);
			BigDecimal overpayment = BigDecimal.ZERO;
			BigDecimal underpayment =rem;
			if (rem.compareTo(BigDecimal.ZERO) == -1) {
				overpayment = rem;
				rem = BigDecimal.ZERO;

			}
			transaction.setTransBalance(rem.abs());
			if (rem.compareTo(BigDecimal.ZERO) == -1 || rem.compareTo(BigDecimal.ZERO) == 0) {
				transaction.setTransSettledAmt(transaction.getTransSettledAmt().add(balance));
				if(ActiveRen!=null)
				authPendingTrans(ActiveRen);
				authTrans = true;
			} else if (rem.compareTo(BigDecimal.ZERO) == 1) {
				transaction.setTransSettledAmt(allocationAmt);
			}
			if(authTrans && transaction.getTranstype().equals("RN")){
				System.out.print("Authorizing renewal");
				transaction.setAuthoriedBy(userUtils.getCurrentUser().getUsername());
				transaction.setAuthorized("Y");
			}
			
			
			BigDecimal receiptAmt = BigDecimal.ZERO;
			
			if (overpayment.compareTo(BigDecimal.ZERO) == 1) {
				receiptAmt = overpayment;
				while (receiptAmt.compareTo(BigDecimal.ZERO) == 1) {
					BigDecimal installAmt = transaction.getInvoice().getInstallmentAmount();
					if (installAmt.compareTo(receiptAmt) == 1)
						installAmt = receiptAmt;
					receiptAmt = receiptAmt.subtract(installAmt);
					
				}
			}

			Transactions transact = createTransaction(receipt.getReceiptNo(), transaction.getTenant(), tran.getRctAmount(), receiptAmt, currency);
			tran.setAllocated("Y");
		     receiptAmt = BigDecimal.ZERO;
			overpayment = overpayment.abs();
			Date wefDate = transaction.getInvoice().getRenewalDate();
			Date wetDate = FormatUtils.addDays(FormatUtils.addMonths(transaction.getInvoice().getRenewalDate(),
					FormatUtils.calculateFrequencyRate(transaction.getInvoice().getFrequency())), -1);
			System.out.println("Over payment "+overpayment);
		
			if (overpayment.compareTo(BigDecimal.ZERO) == 1) {
				receiptAmt = overpayment;
				//BigDecimal  remainder = receiptAmt.remainder(transaction.getInvoice().getInstallmentAmount());
				while (receiptAmt.compareTo(BigDecimal.ZERO) == 1) {
					BigDecimal installAmt = transaction.getInvoice().getInstallmentAmount();
					if (installAmt.compareTo(receiptAmt) == 1)
						installAmt = receiptAmt;
					createRenewal(transaction.getTenant().getTenId(), wefDate, wetDate,invoice.getInvoiceNumber());
					authorizeRenewal(transaction.getTenant().getTenId(), installAmt, transact,invoice.getInvoiceNumber());
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

			}
		     if(underpayment.compareTo(BigDecimal.ZERO)==0){
				createRenewal(transaction.getTenant().getTenId(), wefDate, wetDate,invoice.getInvoiceNumber());
			}
			createSettlements(transaction.getInvoice(), transaction, transact,tran.getRctAmount());
			transactions.add(transaction);
		}
		transRepo.save(transactions);

	}

	@Override
	@Modifying
	@Transactional(readOnly = false, rollbackFor = { BadRequestException.class }, propagation = Propagation.REQUIRED)
	public void createRenewal(Long tenId, Date wefDate, Date wetDate,String invoiceNumber) throws BadRequestException {

		Predicate pred = QTenantInvoice.tenantInvoice.tenant.tenId.eq(tenId)
				.and(QTenantInvoice.tenantInvoice.invoiceNumber.eq(invoiceNumber))
				.and(QTenantInvoice.tenantInvoice.currentStatus.eq("A"));
		
		if (invoiceRepo.count(pred) > 1) {
			throw new BadRequestException(
					"Cannot create a renewal for this tenant...More than one active transaction exist..."+invoiceRepo.count(pred));
		}
		TenantInvoice invoice = invoiceRepo.findOne(pred);
		Long count = invoiceRepo.count(QTenantInvoice.tenantInvoice.invoiceNumber.eq(invoiceNumber));
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
		TenantInvoice ren = invoiceRepo.save(renewal);
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
		trans.setInvoice(ren);
		transRepo.save(trans);
	}

	@Override
	@Modifying
	@Transactional(readOnly = false, rollbackFor = { BadRequestException.class }, propagation = Propagation.REQUIRED)
	public void authorizeRenewal(Long tenId, BigDecimal rctAmount, Transactions receipt,String invoiceNumber) throws BadRequestException {
		Predicate renPred = QTenantInvoice.tenantInvoice.currentStatus.eq("RN")
				.and(QTenantInvoice.tenantInvoice.status.eq("R"))
				.and(QTenantInvoice.tenantInvoice.invoiceNumber.eq(invoiceNumber));
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
				.and(QTransactions.transactions.authorized.eq("R"))
				.and(QTransactions.transactions.refno.eq(invoiceNumber));
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
		createSettlements(latestRenewal, trans, receipt,rctAmount);
	}

	@Override
	@Modifying
	@Transactional(readOnly = false, rollbackFor = {
			BadRequestException.class }, propagation = Propagation.REQUIRES_NEW)
	public void createSettlements(TenantInvoice invoice, Transactions debit, Transactions credit,BigDecimal amount)
			throws BadRequestException {
		ReceiptSettlementDetails settlement = new ReceiptSettlementDetails();
		settlement.setCredit(credit);
		settlement.setDebit(debit);
		settlement.setInvoice(invoice);
		settlement.setAllocatedAmt(amount);
		settlementRepo.save(settlement);

	}

	@Override
	public Transactions createTransaction(String refNo, TenantDef tenant,BigDecimal rctAmt,BigDecimal rctdAmt,Currencies currency) throws BadRequestException {
		Transactions transact = new Transactions();
		transact.setAuthoriedBy(userUtils.getCurrentUser().getUsername());
		transact.setAuthorized("Y");
		transact.setRefno(refNo);
		transact.setTenant(tenant);
		transact.setTransAmount(rctAmt.abs());
		transact.setTransBalance(rctdAmt);
		transact.setTransCommission(BigDecimal.ZERO);
		transact.setTransCurrency(currency);
		transact.setTransDC("C");
		transact.setTransNetAmt(rctAmt.abs());
		transact.setTransTaxes(BigDecimal.ZERO);
		transact.setTranstype("RCT");
		transact.setTransSettledAmt(rctAmt);
		transact.setTransDate(new Date());
		Transactions createdTrans = transRepo.save(transact);
		return createdTrans;
	}
	
	private void authPendingTrans(TenantInvoice latestRenewal) throws BadRequestException{
		latestRenewal.setAuthBy(userUtils.getCurrentUser());
		latestRenewal.setStatus("A");
		invoiceRepo.save(latestRenewal);
	}

}
