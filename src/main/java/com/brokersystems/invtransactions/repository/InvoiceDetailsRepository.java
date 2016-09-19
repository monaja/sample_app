package com.brokersystems.invtransactions.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.invtransactions.model.TenantInvoiceDetails;
import com.brokersystems.setups.model.AccountDef;

public interface InvoiceDetailsRepository extends  PagingAndSortingRepository<TenantInvoiceDetails, Long>, QueryDslPredicateExecutor<TenantInvoiceDetails> {

}
