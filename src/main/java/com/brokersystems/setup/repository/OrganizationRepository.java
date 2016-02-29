package com.brokersystems.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brokersystems.setups.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	
	

}
