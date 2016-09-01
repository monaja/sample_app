package com.brokersystems.setup.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.PaymentModes;

public interface PaymentModeRepo extends  PagingAndSortingRepository<PaymentModes, Long>, QueryDslPredicateExecutor<PaymentModes>{

}
