package com.brokersystems.invtransactions.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.setups.model.RentalUnitCharges;

public interface InvoiceRepository extends  PagingAndSortingRepository<TenantInvoice, Long>, QueryDslPredicateExecutor<TenantInvoice> {

	Optional<TenantInvoice> findByInvoiceId(Long invoiceId);
	
	@Query(nativeQuery=true,value="select count(1) from tenant_invoices where invoice_ten_id=:tenantId and invoice_status in ('A','D')")
	public Long getActiveTenancyCount(@Param("tenantId") long tenantId);
}
