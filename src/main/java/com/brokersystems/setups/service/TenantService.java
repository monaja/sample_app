package com.brokersystems.setups.service;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.TenantDef;

/**
 * Tenant Service
 * Used for crud services for tenant operations
 * @author Peter
 *
 */
public interface TenantService {
	
	
	DataTablesResult<TenantDef> findAllTenants(DataTablesRequest request)  throws IllegalAccessException;
	
    void defineTenant(TenantDef tenant);
	
	void deleteTenant(Long tenId);

}
