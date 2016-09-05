package com.brokersystems.setups.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setup.repository.LandlordRepository;
import com.brokersystems.setups.model.QLandlord;
import com.brokersystems.setups.model.Landlord;
import com.brokersystems.setups.service.LandlordService;

@Service
public class LandlordServiceImpl implements LandlordService{
	
	@Autowired
	private LandlordRepository tenantRepo;
	
	

	@Override
	public DataTablesResult<Landlord> findAllLandlords(DataTablesRequest request) throws IllegalAccessException {
		Page<Landlord> page = tenantRepo.findAll(request.searchPredicate(QLandlord.landlord), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	public void defineLandlord(Landlord tenant) throws BadRequestException {
		 if(tenantRepo.findAll().spliterator().estimateSize() > 0){
			 throw new BadRequestException("Can only define one Landlord in the system");
		 }
		tenantRepo.save(tenant);
		
	}

	@Override
	public void deleteLandlord(Long tenCode) {
		tenantRepo.delete(tenCode);
		
	}

	@Override
	public Landlord getLandlordDetails(Long tenantId) {
		return tenantRepo.findOne(tenantId);
	}

	

}
