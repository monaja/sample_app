package com.brokersystems.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.brokersystems.setups.model.Town;

public interface TownRepository extends JpaRepository<Town, Integer>, QueryDslPredicateExecutor<Town> {
	
	
}
