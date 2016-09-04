package com.brokersystems.setups.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setups.model.AccountTypes;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.RateTypes;
import com.brokersystems.setups.model.RentalStructure;
import com.brokersystems.setups.model.RentalUnitCharges;
import com.brokersystems.setups.model.RentalUnits;
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
	
	DataTablesResult<RentalStructure> findAllStructures(long branchId,DataTablesRequest request)  throws IllegalAccessException;
	
	RentalStructure defineRentalStruct(RentalStructure struct);
	
	void deleteRentalStruct(Long structId);
	
	DataTablesResult<RentalUnits> findAllRentalUnits(long rentalId,DataTablesRequest request)  throws IllegalAccessException;
	
    void defineRentalUnits(RentalUnits unit) throws BadRequestException;
	
	void deleteRentalUnit(Long unitId);
	
	 Page<OrgBranch> findBranchForSelect(String paramString, Pageable paramPageable);
	 
	 Page<UnitTypes> findUnitsForSelect(String paramString, Pageable paramPageable);
	 
	 RentalStructure getStructureDetails(Long rentalId);
	 
	 DataTablesResult<RentalUnitCharges> findRentalUnitCharges(long renId,DataTablesRequest request)  throws IllegalAccessException;
	 
	 void defineRentalCharges(RentalUnitCharges charge) throws BadRequestException ;
		
     void deleteRentalCharge(Long chargeId);
     
     Page<RateTypes> findRatesForSelect(String paramString, Pageable paramPageable);
	
     DataTablesResult<PaymentModes> findAllPaymentModes(DataTablesRequest request)  throws IllegalAccessException;
 	
     void definePaymentMode(PaymentModes mode) throws BadRequestException;
 	
 	 void deletePaymentMode(Long pmId);
 	
 	 DataTablesResult<AccountTypes> findAllAccountTypes(DataTablesRequest request)  throws IllegalAccessException;
 	 
 	void defineAccountType(AccountTypes accType) throws BadRequestException;
 	
	void deleteAccountType(Long acctId);

}
