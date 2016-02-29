package com.brokersystems.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.brokersystems.setups.model.BrokerEntity;

public interface EntityRepository extends JpaRepository<BrokerEntity, Long>, QueryDslPredicateExecutor<BrokerEntity> {

}
