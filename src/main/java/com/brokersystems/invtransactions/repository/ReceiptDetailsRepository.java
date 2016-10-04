package com.brokersystems.invtransactions.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.invtransactions.model.ReceiptTransDtls;

public interface ReceiptDetailsRepository extends  PagingAndSortingRepository<ReceiptTransDtls, Long>, QueryDslPredicateExecutor<ReceiptTransDtls> {

}
