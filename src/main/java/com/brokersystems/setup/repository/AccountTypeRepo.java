package com.brokersystems.setup.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.AccountTypes;

public interface AccountTypeRepo extends  PagingAndSortingRepository<AccountTypes, Long>, QueryDslPredicateExecutor<AccountTypes>{

}
