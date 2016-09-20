package com.brokersystems.invtransactions.repository;

import java.util.Optional;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.invtransactions.model.TenantInvoice;

public interface InvoiceRepository extends  PagingAndSortingRepository<TenantInvoice, Long>, QueryDslPredicateExecutor<TenantInvoice> {

	Optional<TenantInvoice> findByInvoiceId(Long invoiceId);
	
}
