package com.brokersystems.setup.repository;

import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public abstract interface CountyRepository
  extends JpaRepository<County, Long>, QueryDslPredicateExecutor<County>
{
  public  Page<County> findByCountyNameLikeIgnoreCaseAndCountry(String paramString, Pageable paramPageable, Country paramCountry);
  
  
}
