package com.brokersystems.setup.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.brokersystems.setups.model.County;


public interface CountyRepository extends JpaRepository<County, Integer>, QueryDslPredicateExecutor<County> {
	
	Page<County> findByCountyNameLikeIgnoreCaseAndCountyId(String countyName,Pageable pageable,long couId);

}
