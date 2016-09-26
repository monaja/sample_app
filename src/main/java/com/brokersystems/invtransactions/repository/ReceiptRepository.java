package com.brokersystems.invtransactions.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.invtransactions.model.ReceiptTrans;

public interface ReceiptRepository extends  PagingAndSortingRepository<ReceiptTrans, Long>, QueryDslPredicateExecutor<ReceiptTrans> {

}
