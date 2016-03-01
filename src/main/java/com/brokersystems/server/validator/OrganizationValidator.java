package com.brokersystems.server.validator;

import org.springframework.stereotype.Component;

import com.brokersystems.server.exception.BadRequestException;


@Component
public class OrganizationValidator {

	public void validateSelectCountiesForCountry(Long countryId) throws BadRequestException {
        if (countryId == null) {
            throw new BadRequestException("Country id is required");
        }
    }
}
