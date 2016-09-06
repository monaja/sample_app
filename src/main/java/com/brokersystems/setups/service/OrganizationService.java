package com.brokersystems.setups.service;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.OrgRegions;
import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * Organization Service
 * Mainly used for crud service on organization setups
 * @author Peter
 *
 */
public abstract interface OrganizationService
{
  public abstract Organization getOrganizationDetails();
  
  public abstract void createOrganization(Organization paramOrganization);
  
  public abstract DataTablesResult<Country> findCountryDatatables(DataTablesRequest paramDataTablesRequest)
    throws IllegalAccessException;
  
  public abstract DataTablesResult<County> findCountiesByCountry(long paramLong, DataTablesRequest paramDataTablesRequest)
    throws IllegalAccessException;
  
  public abstract DataTablesResult<Town> findTownsByCounty(long paramLong, DataTablesRequest paramDataTablesRequest)
    throws IllegalAccessException;
  
  public abstract DataTablesResult<Currencies> findCurrencies(DataTablesRequest paramDataTablesRequest)
    throws IllegalAccessException;
  
  public Page<User> findUsersForSelect(String term, Pageable pageable);
  
  public abstract DataTablesResult<OrgBranch> findOrgBranches(long paramLong, DataTablesRequest paramDataTablesRequest)
    throws IllegalAccessException;
  
  
  public abstract void createRegionBranch(OrgBranch paramOrgBranch);
  
  
  public abstract void createOrgRegion(OrgRegions region);
  
  public abstract void deleteOrgBranch(Long paramLong);
  
  public abstract void deleteOrgRegion(Long regCode);
  
  public abstract Page<Country> findCountryForSelect(String paramString, Pageable paramPageable);
  
  public abstract Page<County> findCountyForSelect(String paramString, Pageable paramPageable, long paramLong);
  
  public abstract Page<Town> findTownForSelect(String paramString, Pageable paramPageable, long paramLong);
  
  public abstract Page<Currencies> findCurrenciesForSelect(String paramString, Pageable paramPageable);
  
  public DataTablesResult<OrgRegions> findOrgRegions(long orgCode, DataTablesRequest request) throws IllegalAccessException;
}
