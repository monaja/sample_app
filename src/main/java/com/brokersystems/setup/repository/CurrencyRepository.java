package com.brokersystems.setup.repository;

import com.brokersystems.setups.model.Currencies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public abstract interface CurrencyRepository
  extends JpaRepository<Currencies, Long>, QueryDslPredicateExecutor<Currencies>
{
	
  public  Page<Currencies> findByCurNameLikeIgnoreCaseAndEnabled(String paramString, Pageable paramPageable,boolean enabled);

}
