package com.brokersystems.server.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.brokersystems.setups.model.RentalStructure;
@Component
public class RentalStructValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RentalStructure.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		RentalStructure holder = (RentalStructure)obj;
		if(holder.getFile()!=null){
			if(holder.getFile().getSize()> 20000){
				errors.rejectValue("file", "file.toobig");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"houseId", "missing.houseId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"houseName", "missing.housename");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"noOfFloors", "missing.nooffloors");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"noOfUnits", "missing.noofunits");
		
	}

}
