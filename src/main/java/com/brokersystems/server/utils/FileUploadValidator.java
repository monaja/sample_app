package com.brokersystems.server.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.brokersystems.setups.model.Organization;

@Component
public class FileUploadValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		 return Organization.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		 
		Organization holder = (Organization)obj;
		if(holder.getFile()!=null){
			if(holder.getFile().getSize()> 10000){
				errors.rejectValue("file", "file.toobig");
			}
		}
		
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currency.curCode", "missing.currency");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"orgName", "missing.orgname");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"orgShtDesc", "missing.orgshtdesc");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.country.couCode", "missing.orgcountry");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.county.countyId", "missing.orgcounty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.town.ctCode", "missing.orgtown");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.addAddress", "missing.address");
	}

}
