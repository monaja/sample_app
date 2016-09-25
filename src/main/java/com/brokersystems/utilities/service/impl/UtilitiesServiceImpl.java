package com.brokersystems.utilities.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.service.OrganizationService;
import com.brokersystems.utilities.service.UtilitiesService;

@Service
public class UtilitiesServiceImpl implements UtilitiesService {
	
	@Autowired
	private OrganizationService organizationService;

	@Override
	public int getCurrencyFractionUnits() {
		 Organization organization  = organizationService.getOrganizationDetails();
		return organization.getCurrency().getFractionUnits();
	}

}
