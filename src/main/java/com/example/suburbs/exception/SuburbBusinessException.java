package com.example.suburbs.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Exception that are raised during the business logic execution in Suburb API.
 * This can also be used to raise a payload validation error.
 * 
 * @author pankaj.yadav
 */
@JsonIgnoreProperties({ "cause", "stackTrace", "localizedMessage", "message", "suppressed" })
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuburbBusinessException extends RuntimeException {

	private transient List<SuburbError> suburbErrors;

	/**
	 * Constructor.
	 * 
	 * @param message error message
	 * @param cause {@link Throwable}
	 */
	public SuburbBusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param message error message
	 */
	public SuburbBusinessException(final String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * 
	 * @param cause {@link Throwable}
	 */
	public SuburbBusinessException(final Throwable cause) {
	}

}
