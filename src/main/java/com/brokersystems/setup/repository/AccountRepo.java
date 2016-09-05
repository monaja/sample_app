package com.brokersystems.setup.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.AccountDef;

public interface AccountRepo extends  PagingAndSortingRepository<AccountDef, Long>, QueryDslPredicateExecutor<AccountDef> {

}
