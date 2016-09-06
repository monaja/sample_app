package com.brokersystems.setup.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.TenantDef;

public interface TenantRepository extends  PagingAndSortingRepository<TenantDef, Long>, QueryDslPredicateExecutor<TenantDef> {

}
