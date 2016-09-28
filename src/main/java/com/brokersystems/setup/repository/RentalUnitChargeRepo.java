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
	
	public static final String MYSQL_NATIVE_RENTAL_CHARGES_QRY = "select * from rental_units_charges where charge_group = :groupCode and :invoiceDate between charge_wef and ifnull(charge_wet,now())";
	
	public static final String ORACLE_NATIVE_RENTAL_CHARGES_QRY = "select * from rental_units_charges where charge_group = :groupCode and :invoiceDate between charge_wef and NVL(charge_wet,sysdate)";
	
	public static final String POSTGRE_NATIVE_RENTAL_CHARGES_QRY = "select * from rental_units_charges where charge_group = :groupCode and :invoiceDate between charge_wef and coalesce(charge_wet,current_timestamp)";
	
    public static final String MYSQL_NATIVE_NEW_RENTAL_CHARGES_QRY = "select * from rental_units_charges where charge_group = :groupCode and :invoiceDate between charge_wef and ifnull(charge_wet,now()) and charge_id NOT IN (select inv_det_charge_id from tenant_invoice_details where inv_ten_code =:tencode)";
	
	public static final String ORACLE_NATIVE_NEW_RENTAL_CHARGES_QRY = "select * from rental_units_charges where charge_group = :groupCode and :invoiceDate between charge_wef and NVL(charge_wet,sysdate) and charge_id NOT IN (select inv_det_charge_id from tenant_invoice_details where inv_ten_code =:tencode)";
	
	public static final String POSTGRE_NATIVE_NEW_RENTAL_CHARGES_QRY = "select * from rental_units_charges where charge_group = :groupCode and :invoiceDate between charge_wef and coalesce(charge_wet,current_timestamp) and charge_id NOT IN (select inv_det_charge_id from tenant_invoice_details where inv_ten_code =:tencode)";
	
	
	@Query(nativeQuery=true,value=MYSQL_NATIVE_RENTAL_CHARGES_QRY)
	public List<RentalUnitCharges> getActiveUnitCharges(@Param("groupCode") long groupCode, @Param("invoiceDate") Date invoiceDate);
	
	
	@Query(nativeQuery=true,value=MYSQL_NATIVE_NEW_RENTAL_CHARGES_QRY)
	public List<RentalUnitCharges> getNewActiveCharges(@Param("groupCode") long groupCode, @Param("invoiceDate") Date invoiceDate,@Param("tencode") long tencode);

}
