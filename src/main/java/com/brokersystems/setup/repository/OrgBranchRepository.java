package com.brokersystems.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.brokersystems.setups.model.OrgBranch;

public interface OrgBranchRepository extends JpaRepository<OrgBranch,Long>, QueryDslPredicateExecutor<OrgBranch> {
	
	
	

}
