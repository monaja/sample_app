package com.brokersystems.setup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.brokersystems.setups.model.Product;

public interface ProductsRepository extends JpaRepository<Product, Long>, QueryDslPredicateExecutor<Product> {
	

}
