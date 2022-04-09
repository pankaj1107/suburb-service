package com.example.suburbs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Suburb Exception Handler.
 * 
 * @author pankaj.yadav
 *
 */
@ControllerAdvice
@Slf4j
public class SuburbExceptionHandler extends ResponseEntityExceptionHandler {

    /** Handle suburb application error.
     *
     * @param <T>
     *            the generic type
     * @param ex
     *            the ex
     * @return the response entity */
    @ExceptionHandler(SuburbBusinessException.class)
    public ResponseEntity<SuburbBusinessException> handleTimaticBusinessException(final SuburbBusinessException ex) {
    	log.error("Exception occured while processing request ", ex);
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ex);
    }

  
}