package com.brokersystems.errorhandler;

import java.sql.SQLException;

import javax.persistence.PersistenceException;

import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	    ResponseEntity handle(Exception   e) {
	        return new ResponseEntity(e.getCause().getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	    }
	
	
	@ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity handle(IllegalArgumentException   e) {
        return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	 

    @ExceptionHandler({DataAccessException.class})
    ResponseEntity databaseError(DataAccessException   e) {
    		  return new ResponseEntity(e.getMostSpecificCause().getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    	
    }
    
   

}
