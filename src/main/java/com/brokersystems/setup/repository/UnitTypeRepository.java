package com.brokersystems.setup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.UnitTypes;

public interface UnitTypeRepository extends PagingAndSortingRepository<UnitTypes, Long>, QueryDslPredicateExecutor<UnitTypes>{

	Page<UnitTypes> findByUnitNameLikeIgnoreCase(String unitName,Pageable pageable);
	
}
