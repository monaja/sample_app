package com.brokersystems.setup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;

public interface OrgBranchRepository extends  PagingAndSortingRepository<OrgBranch, Long>, QueryDslPredicateExecutor<OrgBranch> {
	
	public  Page<OrgBranch> findByObShtDescLikeIgnoreCase(String paramString, Pageable paramPageable);

}
 