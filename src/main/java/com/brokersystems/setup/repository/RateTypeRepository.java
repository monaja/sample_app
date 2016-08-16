package com.brokersystems.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.brokersystems.setups.model.RateTypes;


public interface RateTypeRepository extends JpaRepository<RateTypes, Long>, QueryDslPredicateExecutor<RateTypes>
{

}
