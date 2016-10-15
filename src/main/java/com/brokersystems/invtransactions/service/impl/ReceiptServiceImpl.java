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
import com.brokersystems.utilities.service.UtilitiesService;
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
	private UtilitiesService utilities;

	@Override
	public DataTablesResult<ReceiptTrans> findAllReceipts(DataTablesRequest request) throws IllegalAccessException {
		Page<ReceiptTrans> page = receiptRepo.findAll(request.searchPredicate(QReceiptTrans.receiptTrans), request);
		return new DataTablesResult(request, page);
	}

	@Override
	public DataTablesResult<Transactions> findReceiptTransactions(DataTablesRequest request, String tenantFname,
			String otherName, String invoiceNumber) throws IllegalAccessException {
		if(tenantFname==null) tenantFname="";
		if(otherName==null) otherName="";
		Predicate pred = QTransactions.transactions.transDC.eq("D")
				.and(QTransactions.transactions.refno.containsIgnoreCase(invoiceNumber))
				.and(QTransactions.transactions.transBalance.gt(BigDecimal.ZERO))
				.and(QTransactions.transactions.tenant.isNotNull())
				.and(QTransactions.transactions.tenant.fname.containsIgnoreCase(tenantFname))
				.and(QTransactions.transactions.tenant.otherNames.containsIgnoreCase(otherName));// .and(tenant.fname.startsWithIgnoreCase(tenantFname).or(tenant.otherNames.startsWithIgnoreCase(otherName)).or(QTransactions.transactions.refno.eq(invoiceNumber)));
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
		BigDecimal totalAllocAmount = BigDecimal.ZERO;
		for (ReceiptTransDtls tran : receipt.getDetails()) {
			Transactions transaction = transRepo.findOne(tran.getInvoiceCode());
			tran.setReceipt(receipt);
			tran.setInvoice(transaction.getInvoice());
			tran.setInvoiceRevision(transaction.getInvoice().getRevisionNumber());
			tran.setRctType("INV");
			tran.setRctDC("C");
			transDtls.add(tran);
			totalAllocAmount = totalAllocAmount.add(tran.getRctAmount());
		}
		if (totalAllocAmount.compareTo(receipt.getReceiptAmount()) != 0) {
			throw new BadRequestException("Total Allocation Amount " + totalAllocAmount + " and Receipt amount "
					+ receipt.getReceiptAmount() + " doesnt tally....");
		}
		rctDetailsRepo.save(transDtls);
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
	@Transactional(readOnly = false, rollbackFor = { BadRequestException.class })
	public void markReceiptPrinted(Long receiptId) throws BadRequestException {
		ReceiptTrans receipt = receiptRepo.findOne(receiptId);
		receipt.setPrinted("Y");
		receiptRepo.save(receipt);
		utilities.allocateReceipt(receiptId);
	}

}
