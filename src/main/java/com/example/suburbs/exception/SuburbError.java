package com.example.suburbs.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Custom Suburb Error Type that needs to be sent by every REST API
 * in case of error.
 *
 * @author pankaj.yadav
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuburbError {

	/** The status. */
	private HttpStatus status;

	/** The time stamp. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;

	/** The error code. */
	private String errorCode;

	/** The error details. */
	private String details;

	/** The error meta data. */
	private Map<String, Object> metadata = new HashMap<>();

	/**
	 * Prepare suburb error on the basis of provided error code, message,
	 * HTTP status code and error meta data.
	 * 
	 * @param status    HTTP status code
	 * @param errorCode error code
	 * @param details   error details
	 * @param metadata  error metadata
	 * @return suburb error
	 */
	public static SuburbError prepareCloudDatalakeError(final HttpStatus status, final String errorCode,
			final String details, final Map<String, Object> metadata) {
		final SuburbError suburbError = new SuburbError();
		suburbError.setDateTime(LocalDateTime.now());
		suburbError.setErrorCode(errorCode);
		suburbError.setDetails(details);
		suburbError.setStatus(status);
		suburbError.setMetadata(metadata);
		return suburbError;
	}

}
