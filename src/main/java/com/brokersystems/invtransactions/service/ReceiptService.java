package com.brokersystems.invtransactions.service;

import com.brokersystems.invtransactions.model.ReceiptTrans;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;

public interface ReceiptService {
	
	DataTablesResult<ReceiptTrans> findAllReceipts(DataTablesRequest request) throws IllegalAccessException;

}
