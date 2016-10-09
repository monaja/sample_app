package com.brokersystems.invtransactions.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.invtransactions.model.ReceiptSettlementDetails;

public interface SettlementRepo extends  PagingAndSortingRepository<ReceiptSettlementDetails, Long>, QueryDslPredicateExecutor<ReceiptSettlementDetails> {

	
	
	
}
