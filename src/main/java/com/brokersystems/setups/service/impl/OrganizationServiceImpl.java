package com.brokersystems.setups.service.impl;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setup.repository.CountryRepository;
import com.brokersystems.setup.repository.CountyRepository;
import com.brokersystems.setup.repository.CurrencyRepository;
import com.brokersystems.setup.repository.OrgBankRepository;
import com.brokersystems.setup.repository.OrgBranchRepository;
import com.brokersystems.setup.repository.OrganizationRepository;
import com.brokersystems.setup.repository.RegionRepository;
import com.brokersystems.setup.repository.TownRepository;
import com.brokersystems.setups.model.Bank;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.OrgRegions;
import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.QBank;
import com.brokersystems.setups.model.QCountry;
import com.brokersystems.setups.model.QCounty;
import com.brokersystems.setups.model.QCurrencies;
import com.brokersystems.setups.model.QOrgBranch;
import com.brokersystems.setups.model.QOrgRegions;
import com.brokersystems.setups.model.QOrganization;
import com.brokersystems.setups.model.QTown;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.service.OrganizationService;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.NumberPath;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationServiceImpl
  implements OrganizationService
{
  @Autowired
  private OrganizationRepository orgRepo;
  @Autowired
  private CountryRepository countryRepo;
  @Autowired
  private CountyRepository countyRepo;
  @Autowired
  private TownRepository townRepo;
  @Autowired
  private CurrencyRepository currencyrepo;
  @Autowired
  private OrgBankRepository orgBankrepo;
  @Autowired
  private OrgBranchRepository orgBranchrepo;
  @Autowired
  private RegionRepository regionRepo;
  
  @Transactional(readOnly=true)
  public Organization getOrganizationDetails()
  {
    List<Organization> orgDetails = this.orgRepo.findAll();
    if (!orgDetails.isEmpty()) {
      return (Organization)orgDetails.get(0);
    }
    return new Organization();
  }
  
  public void createOrganization(Organization org)
  {
    this.orgRepo.save(org);
  }
  
  @Transactional(readOnly=true)
  public DataTablesResult<Country> findCountryDatatables(DataTablesRequest request)
    throws IllegalAccessException
  {
    Page<Country> page = this.countryRepo.findAll(request.searchPredicate(QCountry.country), request);
    return new DataTablesResult(request, page);
  }
  
  @Transactional(readOnly=true)
  public DataTablesResult<County> findCountiesByCountry(long countryCode, DataTablesRequest request)
    throws IllegalAccessException
  {
    QCountry country = QCounty.county.country;
    BooleanExpression pred = country.couCode.eq(Long.valueOf(countryCode));
    Page<County> page = this.countyRepo.findAll(pred.and(request.searchPredicate(QCounty.county)), request);
    return new DataTablesResult(request, page);
  }
  
  @Transactional(readOnly=true)
  public DataTablesResult<Town> findTownsByCounty(long countyCode, DataTablesRequest request)
    throws IllegalAccessException
  {
    QCounty county = QTown.town.county;
    BooleanExpression pred = county.countyId.eq(Long.valueOf(countyCode));
    Page<Town> page = this.townRepo.findAll(pred.and(request.searchPredicate(QTown.town)), request);
    return new DataTablesResult(request, page);
  }
  
  @Transactional(readOnly=true)
  public DataTablesResult<Currencies> findCurrencies(DataTablesRequest request)
    throws IllegalAccessException
  {
    Page<Currencies> page = this.currencyrepo.findAll(request.searchPredicate(QCurrencies.currencies), request);
    return new DataTablesResult(request, page);
  }
  
  @Transactional(readOnly=true)
  public DataTablesResult<OrgRegions> findOrgRegions(long orgCode, DataTablesRequest request)
    throws IllegalAccessException
  {
	QOrganization org  = QOrgRegions.orgRegions.organization;
    BooleanExpression pred =org.orgCode.eq(Long.valueOf(orgCode));
    Page<OrgRegions> page = this.regionRepo.findAll(pred.and(request.searchPredicate(QOrgRegions.orgRegions)), request);
    return new DataTablesResult(request, page);
  }
  
  
  @Transactional(readOnly=true)
  public DataTablesResult<OrgBranch> findOrgBranches(long regCode, DataTablesRequest request)
    throws IllegalAccessException
  {
    QOrgRegions reg = QOrgBranch.orgBranch.region;
    BooleanExpression pred =reg.regCode.eq(Long.valueOf(regCode));
    Page<OrgBranch> page = this.orgBranchrepo.findAll(pred.and(request.searchPredicate(QOrgBranch.orgBranch)), request);
    return new DataTablesResult(request, page);
  }
  
  @Transactional(readOnly=true)
  public DataTablesResult<Bank> findOrgBanks(long orgCode, DataTablesRequest request)
    throws IllegalAccessException
  {
    QOrganization org = QBank.bank.organization;
    BooleanExpression pred = org.orgCode.eq(Long.valueOf(orgCode));
    Page<Bank> page = this.orgBankrepo.findAll(pred.and(request.searchPredicate(QBank.bank)), request);
    return new DataTablesResult(request, page);
  }
  
  @Modifying
  @Transactional(readOnly=false)
  public void createOrgBranch(OrgBranch branch)
  {
    this.orgBranchrepo.save(branch);
  }
  
  @Modifying
  @Transactional(readOnly=false)
  public void createOrgBank(Bank bank)
  {
    this.orgBankrepo.save(bank);
  }
  
  @Modifying
  @Transactional(readOnly=false)
  public void deleteOrgBranch(Long branchCode)
  {
    this.orgBranchrepo.delete(branchCode);
  }
  
  @Modifying
  @Transactional(readOnly=false)
  public void deleteOrgBank(Long bankCode)
  {
    this.orgBankrepo.delete(bankCode);
  }
  
  public Page<Country> findCountryForSelect(String term, Pageable pageable)
  {
    term = "%" + StringUtils.defaultString(term) + "%";
    return this.countryRepo.findByCouNameLikeIgnoreCase(term, pageable);
  }
  
  public Page<County> findCountyForSelect(String term, Pageable pageable, long couId)
  {
    term = "%" + StringUtils.defaultString(term) + "%";
    Country country = (Country)this.countryRepo.findOne(Long.valueOf(couId));
    return this.countyRepo.findByCountyNameLikeIgnoreCaseAndCountry(term, pageable, country);
  }
  
  public Page<Town> findTownForSelect(String term, Pageable pageable, long countyId)
  {
    term = "%" + StringUtils.defaultString(term) + "%";
    County county = (County)this.countyRepo.findOne(Long.valueOf(countyId));
    return this.townRepo.findByCtNameLikeIgnoreCaseAndCounty(term, pageable, county);
  }
  
  public Page<Currencies> findCurrenciesForSelect(String term, Pageable pageable)
  {
    term = "%" + StringUtils.defaultString(term) + "%";
    return this.currencyrepo.findByCurNameLikeIgnoreCase(term, pageable);
  }
}
