package com.brokersystems.setups.service;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.BrokerEntity;

public interface EntityService {

	DataTablesResult<BrokerEntity> entities(DataTablesRequest request) throws IllegalAccessException;
	
	
}
