package com.brokersystems.setups.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setup.repository.TenantRepository;
import com.brokersystems.setups.model.QTenantDef;
import com.brokersystems.setups.model.TenantDef;
import com.brokersystems.setups.service.TenantService;

@Service
public class TenantServiceImpl implements TenantService {
	
	@Autowired
	private TenantRepository tenantRepo;

	@Override
	@Transactional(readOnly = true)
	public DataTablesResult<TenantDef> findAllTenants(DataTablesRequest request) throws IllegalAccessException {
		Page<TenantDef> page = tenantRepo.findAll(request.searchPredicate(QTenantDef.tenantDef), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	@Modifying
    @Transactional(readOnly=false)
	public void defineTenant(TenantDef tenant) {
		tenantRepo.save(tenant);
		
	}

	@Override
	@Modifying
    @Transactional(readOnly=false)
	public void deleteTenant(Long tenId) {
		tenantRepo.delete(tenId);
		
	}

	@Override
	@Transactional(readOnly=true)
	public TenantDef getTenantDetails(Long tenId) {
		return tenantRepo.findByTenId(tenId).orElse(new TenantDef());
	}

}
