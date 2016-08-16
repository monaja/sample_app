package com.brokersystems.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.brokersystems.setups.model.UnitTypes;

public interface UnitTypeRepository extends JpaRepository<UnitTypes, Long>, QueryDslPredicateExecutor<UnitTypes>{

}
