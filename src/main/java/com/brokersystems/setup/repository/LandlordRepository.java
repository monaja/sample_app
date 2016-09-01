package com.brokersystems.setup.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.brokersystems.setups.model.Landlord;

public interface LandlordRepository extends  PagingAndSortingRepository<Landlord, Long>, QueryDslPredicateExecutor<Landlord> {

}
