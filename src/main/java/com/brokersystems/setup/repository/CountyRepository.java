package com.brokersystems.setup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;


public interface CountyRepository extends JpaRepository<County, Integer>, QueryDslPredicateExecutor<County> {
	
		

}
