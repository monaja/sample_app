package com.brokersystems.setups.service;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.Bank;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.model.User;


public interface OrganizationService {
	

     Organization getOrganizationDetails();
	
    void createOrganization(Organization org);
	
	DataTablesResult<Country> findCountryDatatables(DataTablesRequest request) throws IllegalAccessException;
	   
	DataTablesResult<County> findCountiesByCountry(long countryCode,DataTablesRequest request) throws IllegalAccessException;
	  
    DataTablesResult<Town> findTownsByCounty(long countyCode, DataTablesRequest request) throws IllegalAccessException;
	
	DataTablesResult<Currencies> findCurrencies(DataTablesRequest request) throws IllegalAccessException;
	
	DataTablesResult<OrgBranch> findOrgBranches(long orgCode, DataTablesRequest request) throws IllegalAccessException;
	
	DataTablesResult<Bank> findOrgBanks(long orgCode, DataTablesRequest request) throws IllegalAccessException;
	
	void createOrgBranch(OrgBranch branch);
	
	void createOrgBank(Bank bank);
	
	void deleteOrgBranch(Long branchCode);
	
	void deleteOrgBank(Long bankCode);
}
