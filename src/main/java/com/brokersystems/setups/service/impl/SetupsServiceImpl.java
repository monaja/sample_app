package com.brokersystems.setups.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setup.repository.CountryRepository;
import com.brokersystems.setup.repository.CountyRepository;
import com.brokersystems.setup.repository.CurrencyRepository;
import com.brokersystems.setup.repository.TownRepository;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgRegions;
import com.brokersystems.setups.model.QCountry;
import com.brokersystems.setups.model.QCounty;
import com.brokersystems.setups.model.QCurrencies;
import com.brokersystems.setups.model.QOrgRegions;
import com.brokersystems.setups.model.QTown;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.service.SetupsService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
public class SetupsServiceImpl implements SetupsService {
	
	@Autowired
	private CurrencyRepository currRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private CountyRepository countyRepo;
	
	@Autowired
	private TownRepository townRepo;

	@Override
	@Transactional(readOnly=true)
	public DataTablesResult<Currencies> findAllCurrencies(DataTablesRequest request) throws IllegalAccessException {
		 Page<Currencies> page = currRepo.findAll(request.searchPredicate(QCurrencies.currencies), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	public void defineCurrency(Currencies currency) {
		currRepo.save(currency);
		
	}

	@Override
	public void deleteCurrency(Long currCode) {
		currRepo.delete(currCode);
		
	}

	@Override
	public DataTablesResult<Country> findAllCountries(DataTablesRequest request) throws IllegalAccessException {
		Page<Country> page = countryRepo.findAll(request.searchPredicate(QCountry.country), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	public void defineCountry(Country country) {
		countryRepo.save(country);
		
	}

	@Override
	public void deleteCountry(Long couCode) {
		countryRepo.delete(couCode);
		
	}

	@Override
	public DataTablesResult<County> findCountiesByCountry(long couCode,DataTablesRequest request) throws IllegalAccessException {
		QCountry country = QCounty.county.country;
		BooleanExpression pred = country.couCode.eq(couCode);
		 Page<County> page = countyRepo.findAll(pred.and(request.searchPredicate(QCounty.county)), request);
		 return new DataTablesResult(request, page);
	}

	@Override
	public void defineCounty(County county) {
		countyRepo.save(county);
		
	}

	@Override
	public void deleteCounty(Long countyCode) {
		countyRepo.delete(countyCode);
		
	}

	@Override
	public DataTablesResult<Town> findTownsByCounty(long countyCode, DataTablesRequest request)
			throws IllegalAccessException {
		QCounty county = QTown.town.county;
		BooleanExpression pred = county.countyId.eq(countyCode);
		 Page<Town> page = townRepo.findAll(pred.and(request.searchPredicate(QTown.town)), request);
		 return new DataTablesResult(request, page);
	}

	@Override
	public void defineTown(Town town) {
		townRepo.save(town);
	}

	@Override
	public void deleteTown(Long townCode) {
		townRepo.delete(townCode);
	}

}
