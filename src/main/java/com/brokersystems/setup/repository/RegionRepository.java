package com.brokersystems.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.brokersystems.setups.model.OrgRegions;

public interface RegionRepository extends JpaRepository<OrgRegions, Long>, QueryDslPredicateExecutor<OrgRegions> {

}
