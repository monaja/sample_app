package com.brokersystems.setup.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.TenAllocations;

public interface TenantAllocRepo  extends  PagingAndSortingRepository<TenAllocations, Long>, QueryDslPredicateExecutor<TenAllocations> {

}
