package com.brokersystems.setups.service;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setups.model.RentalStructure;
import com.brokersystems.setups.model.Landlord;
import com.brokersystems.setups.model.PaymentModes;

public interface LandlordService {
	
    DataTablesResult<Landlord> findAllLandlords(DataTablesRequest request)  throws IllegalAccessException;
	
	void defineLandlord(Landlord tenant) throws BadRequestException;
	
	void deleteLandlord(Long tenCode);
	
	
	Landlord getLandlordDetails(Long tenantId);
	
	
	

}
