package com.brokersystems.invtransactions.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.invtransactions.model.Transactions;

public interface TransactionRepository extends  PagingAndSortingRepository<Transactions, Long>, QueryDslPredicateExecutor<Transactions> {

}
