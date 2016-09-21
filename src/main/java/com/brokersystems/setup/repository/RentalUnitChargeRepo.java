package com.brokersystems.setup.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brokersystems.setups.model.RentalUnitCharges;


public interface RentalUnitChargeRepo extends PagingAndSortingRepository<RentalUnitCharges, Long>, QueryDslPredicateExecutor<RentalUnitCharges> {
	
	public static final String MYSQL_NATIVE_RENTAL_CHARGES_QRY = "select * from rental_units_charges where charge_unit = :unitCode and :invoiceDate between charge_wef and ifnull(charge_wet,now())";
	
	public static final String ORACLE_NATIVE_RENTAL_CHARGES_QRY = "select * from rental_units_charges where charge_unit = :unitCode and :invoiceDate between charge_wef and NVL(charge_wet,sysdate)";
	
	
	@Query(nativeQuery=true,value=MYSQL_NATIVE_RENTAL_CHARGES_QRY)
	public List<RentalUnitCharges> getActiveUnitCharges(@Param("unitCode") long unitCode, @Param("invoiceDate") Date invoiceDate);

}
