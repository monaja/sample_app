package com.brokersystems.setups.service;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.RateTypes;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.model.UnitTypes;

/**
 * This  is used to maintain crud and query services of several setups screens
 * in the system like Currency, Countries, Counties, Citys
 * @author mugenyq
 *
 */
public interface SetupsService {
	
	
	DataTablesResult<Currencies> findAllCurrencies(DataTablesRequest request)  throws IllegalAccessException;
	
	void defineCurrency(Currencies currency);
	
	void deleteCurrency(Long currCode);
	
	DataTablesResult<Country> findAllCountries(DataTablesRequest request)  throws IllegalAccessException;
	
	void defineCountry(Country country);
	
	void deleteCountry(Long couCode);
	
	DataTablesResult<County> findCountiesByCountry(long couCode,DataTablesRequest request)  throws IllegalAccessException;
	
    void defineCounty(County county);
	
	void deleteCounty(Long countyCode);
	
    DataTablesResult<Town> findTownsByCounty(long countyCode,DataTablesRequest request)  throws IllegalAccessException;
	
    void defineTown(Town town);
	
	void deleteTown(Long townCode);
	
	DataTablesResult<RateTypes> findAllRateTypes(DataTablesRequest request)  throws IllegalAccessException;
	
	void defineRateType(RateTypes rateType);
	
	void deleteRateType(Long rateTypeCode);
	
	DataTablesResult<UnitTypes> findAllUnitTypes(DataTablesRequest request)  throws IllegalAccessException;
	
    void defineUnitType(UnitTypes unitType);
	
	void deleteUnitType(Long unitCode);
	
	
	

}
