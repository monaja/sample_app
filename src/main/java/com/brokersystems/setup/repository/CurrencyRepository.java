package com.brokersystems.setup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.brokersystems.setups.model.Currencies;

public interface CurrencyRepository extends JpaRepository<Currencies, Integer>, QueryDslPredicateExecutor<Currencies>  {
	


}
