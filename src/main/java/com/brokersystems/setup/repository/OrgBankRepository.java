package com.brokersystems.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.brokersystems.setups.model.Bank;

public interface OrgBankRepository extends JpaRepository<Bank, Long>, QueryDslPredicateExecutor<Bank> {
	
	
	
}
