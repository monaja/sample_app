package com.brokersystems.setup.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.ChargeRatesGroup;

public interface ChargeRatesGroupRepo  extends  PagingAndSortingRepository<ChargeRatesGroup, Long>, QueryDslPredicateExecutor<ChargeRatesGroup> {
	
	

}
