package com.brokersystems.setups.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setup.repository.CountryRepository;
import com.brokersystems.setup.repository.CountyRepository;
import com.brokersystems.setup.repository.CurrencyRepository;
import com.brokersystems.setup.repository.OrgBankRepository;
import com.brokersystems.setup.repository.OrgBranchRepository;
import com.brokersystems.setup.repository.OrganizationRepository;
import com.brokersystems.setup.repository.TownRepository;
import com.brokersystems.setups.model.Bank;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.QBank;
import com.brokersystems.setups.model.QCountry;
import com.brokersystems.setups.model.QCounty;
import com.brokersystems.setups.model.QCurrencies;
import com.brokersystems.setups.model.QOrgBranch;
import com.brokersystems.setups.model.QOrganization;
import com.brokersystems.setups.model.QTown;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.service.OrganizationService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
public class OrganizationServiceImpl implements OrganizationService {

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
	
		
	//@Cacheable(value="organizationCache")
	@Override
	@Transactional(readOnly = true)
	public Organization getOrganizationDetails() {
		List<Organization> orgDetails = orgRepo.findAll();
		if(!orgDetails.isEmpty())
			return orgDetails.get(0);
		else
		return new Organization();
	}


//	@CacheEvict(value="organizationCache")
	@Modifying
	@Transactional(readOnly = false)
	@Override
	public void createOrganization(Organization org) {
		 orgRepo.save(org);
	}

	@Override
	@Transactional(readOnly = true)
	public DataTablesResult<Country> findCountryDatatables(DataTablesRequest request) throws IllegalAccessException {
		Page<Country> page =countryRepo.findAll(request.searchPredicate(QCountry.country),request);
		return new DataTablesResult<Country>(request, page);
	}


	@Override
	@Transactional(readOnly = true)
	public DataTablesResult<County> findCountiesByCountry(long countryCode, DataTablesRequest request)
			throws IllegalAccessException {
		QCountry country = QCounty.county.country;
		BooleanExpression pred = country.couCode.eq(countryCode);
		 Page<County> page = countyRepo.findAll(pred.and(request.searchPredicate(QCounty.county)),request);
		 return new DataTablesResult<County>(request, page);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public DataTablesResult<Town> findTownsByCounty(long countyCode, DataTablesRequest request)
			throws IllegalAccessException {
		QCounty county = QTown.town.county;
		BooleanExpression pred = county.countyId.eq(countyCode);
		 Page<Town> page = townRepo.findAll(pred.and(request.searchPredicate(QTown.town)),request);
		 return new DataTablesResult<Town>(request, page);
	}


	@Override
	@Transactional(readOnly = true)
	public DataTablesResult<Currencies> findCurrencies(DataTablesRequest request) throws IllegalAccessException {
		Page<Currencies> page  = currencyrepo.findAll(request.searchPredicate(QCurrencies.currencies),request);
		return new DataTablesResult<>(request, page);
	}


	@Override
	@Transactional(readOnly = true)
	public DataTablesResult<OrgBranch> findOrgBranches(long orgCode, DataTablesRequest request)
			throws IllegalAccessException {
		QOrganization org = QOrgBranch.orgBranch.organization;
		BooleanExpression pred = org.orgCode.eq(orgCode);
		Page<OrgBranch> page = orgBranchrepo.findAll(pred.and(request.searchPredicate(QOrgBranch.orgBranch)),request);
		return new DataTablesResult<>(request, page);
	}


	@Override
	@Transactional(readOnly = true)
	public DataTablesResult<Bank> findOrgBanks(long orgCode, DataTablesRequest request)
			throws IllegalAccessException {
		 QOrganization org = QBank.bank.organization;
		 BooleanExpression pred = org.orgCode.eq(orgCode);
		 Page<Bank> page = orgBankrepo.findAll(pred.and(request.searchPredicate(QBank.bank)),request);
		 return new DataTablesResult<>(request, page);
	}

	@Modifying
	@Transactional(readOnly = false)
	@Override
	public void createOrgBranch(OrgBranch branch) {
		 orgBranchrepo.save(branch);
	}

	@Modifying
	@Transactional(readOnly = false)
	@Override
	public void createOrgBank(Bank bank) {
		orgBankrepo.save(bank);
		
	}

	@Modifying
	@Transactional(readOnly = false)
	@Override
	public void deleteOrgBranch(Long branchCode) {
		orgBranchrepo.delete(branchCode);
		
	}

	@Modifying
	@Transactional(readOnly = false)
	@Override
	public void deleteOrgBank(Long bankCode) {
		orgBankrepo.delete(bankCode);
		
	}


	@Override
    public Page<Country> findForSelect(String term, Pageable pageable) {

        term = "%" + StringUtils.defaultString(term) + "%";
        return countryRepo.findByCouNameLikeIgnoreCase(term, pageable);

    }


	@Override
	public Page<County> findCountyForSelect(String term, Pageable pageable,
			long couId) {
		term = "%" + StringUtils.defaultString(term) + "%";
		return countyRepo.findByCountyNameLikeIgnoreCaseAndCountyId(term, pageable, couId);
	}
}
