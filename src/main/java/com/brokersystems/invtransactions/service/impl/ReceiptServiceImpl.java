package com.brokersystems.invtransactions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.brokersystems.invtransactions.model.QReceiptTrans;
import com.brokersystems.invtransactions.model.ReceiptTrans;
import com.brokersystems.invtransactions.repository.ReceiptRepository;
import com.brokersystems.invtransactions.service.ReceiptService;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;

@Service
public class ReceiptServiceImpl implements ReceiptService {
	
	@Autowired
	private ReceiptRepository receiptRepo;

	@Override
	public DataTablesResult<ReceiptTrans> findAllReceipts(DataTablesRequest request) throws IllegalAccessException {
		Page<ReceiptTrans> page = receiptRepo.findAll(request.searchPredicate(QReceiptTrans.receiptTrans), request);
		return new DataTablesResult(request, page);
	}

}
