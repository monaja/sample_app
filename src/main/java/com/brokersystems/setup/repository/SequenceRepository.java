package com.brokersystems.setup.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brokersystems.setups.model.SystemSequence;


public interface SequenceRepository extends  PagingAndSortingRepository<SystemSequence, Long>, QueryDslPredicateExecutor<SystemSequence> {

}
