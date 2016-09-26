package com.brokersystems.invtransactions.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.setups.model.TenantDef;

public interface InvoiceRepository extends  PagingAndSortingRepository<TenantInvoice, Long>, QueryDslPredicateExecutor<TenantInvoice> {

	Optional<TenantInvoice> findByInvoiceId(Long invoiceId);
	
	@Query(nativeQuery=true,value="select count(1) from tenant_invoices where invoice_ten_id=:tenantId and invoice_status in ('A','D')")
	public Long getActiveTenancyCount(@Param("tenantId") long tenantId);
	
	
	@Query("select t from TenantDef t where t.status='A' and (fname like %:search% or otherNames like %:search% or tenantNumber like %:search%) and t NOT IN (select i.tenant from TenantInvoice i where i.currentStatus='A')")
	public Page<TenantDef> findTenantsWithoutContracts(@Param("search") String search, Pageable pageable);
	
	
	
}
