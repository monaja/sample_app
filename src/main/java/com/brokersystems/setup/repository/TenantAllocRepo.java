package com.brokersystems.setup.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brokersystems.setups.model.RentalUnits;
import com.brokersystems.setups.model.TenAllocations;

public interface TenantAllocRepo  extends  PagingAndSortingRepository<TenAllocations, Long>, QueryDslPredicateExecutor<TenAllocations> {
	
	@Query("Select r from RentalUnits r where r.rentalStruct.rentalId = :renId and NOT EXISTS (select t from r.allocations t where t.tenant.tenId=:tenId and t.cancelled='N') ")
	public Page<RentalUnits> findUnallocatedUnits(@Param("renId") Long renId,@Param("tenId") Long tenId,Pageable paramPageable);
	
	@Query("Select r from RentalUnits r where r.rentalStruct.rentalId = :renId and NOT EXISTS (select t from r.allocations t where t.cancelled='N') ")
	public Page<RentalUnits> findUnallocatedUnits(@Param("renId") Long renId,Pageable paramPageable);

}
