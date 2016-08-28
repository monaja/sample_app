package com.brokersystems.setup.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.RentalUnitCharges;


public interface RentalUnitChargeRepo extends PagingAndSortingRepository<RentalUnitCharges, Long>, QueryDslPredicateExecutor<RentalUnitCharges> {

}
