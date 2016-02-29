package com.brokersystems.setups.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setup.repository.EntityRepository;
import com.brokersystems.setups.model.BrokerEntity;
import com.brokersystems.setups.model.QBrokerEntity;
import com.brokersystems.setups.service.EntityService;

@Service
public class EntityServiceImpl implements EntityService {
	
	@Autowired
	private EntityRepository repo;

	@Override
	public DataTablesResult<BrokerEntity> entities(DataTablesRequest request) throws IllegalAccessException {
		Page<BrokerEntity> page =repo.findAll(request.searchPredicate(QBrokerEntity.brokerEntity),request);
		return new DataTablesResult<BrokerEntity>(request, page);
	}

}
