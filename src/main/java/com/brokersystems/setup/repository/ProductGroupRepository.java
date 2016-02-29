package com.brokersystems.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.brokersystems.setups.model.ProductGroup;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long>, QueryDslPredicateExecutor<ProductGroup> {

}
