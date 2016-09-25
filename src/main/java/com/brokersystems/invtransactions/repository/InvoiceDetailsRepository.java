package com.brokersystems.invtransactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brokersystems.invtransactions.model.TenantInvoiceDetails;
import com.brokersystems.setups.model.AccountDef;

public interface InvoiceDetailsRepository extends  PagingAndSortingRepository<TenantInvoiceDetails, Long>, QueryDslPredicateExecutor<TenantInvoiceDetails> {

	     @Query("select t from TenantInvoiceDetails t where t.invoice.invoiceId=:tenId")
		public List<TenantInvoiceDetails> getInvoiceDetails(@Param("tenId") Long tenId);
}
