package com.brokersystems.setup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.brokersystems.setups.model.RentalStructure;

public interface RentalStructRepository extends JpaRepository<RentalStructure, Long>, QueryDslPredicateExecutor<RentalStructure> {
	
	  Optional<RentalStructure> findByRentalId(Long rentalId);

}
