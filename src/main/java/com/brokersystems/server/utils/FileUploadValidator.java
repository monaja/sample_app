package com.brokersystems.server.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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
			if(holder.getFile().getSize()==0){
				errors.rejectValue("file", "missing.file");
			}
		}
		
	}

}
