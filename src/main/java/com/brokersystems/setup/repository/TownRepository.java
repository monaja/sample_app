package com.brokersystems.setup.repository;

import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Town;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public abstract interface TownRepository
  extends JpaRepository<Town, Long>, QueryDslPredicateExecutor<Town>
{
  public  Page<Town> findByCtNameLikeIgnoreCaseAndCounty(String paramString, Pageable paramPageable, County paramCounty);
}
