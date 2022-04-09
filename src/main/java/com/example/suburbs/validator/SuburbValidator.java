package com.example.suburbs.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.suburbs.exception.SuburbBusinessException;
import com.example.suburbs.exception.SuburbError;
import com.example.suburbs.model.SearchSuburbRequest;

/**
 * This class is used to hold methods related to validation of payload requests.
 * 
 * @author pankaj.yadav
 *
 */
@Component
public class SuburbValidator {

	/**
	 * Validate search suburb request.
	 * 
	 * @param searchSuburbReq 
	 * 		search suburb request.
	 */
	public void validateSearchSuburbReq(final SearchSuburbRequest searchSuburbReq) {
		final Integer startPostCodeInReq = searchSuburbReq.getStartPostCode();
		final Integer endPostCodeInReq = searchSuburbReq.getEndPostCode();
		if (startPostCodeInReq == null || endPostCodeInReq == null) {
			final List<SuburbError> suburbErrors = new ArrayList<>();
			final Map<String, Object> errorMetaData = new HashMap<>();
			errorMetaData.put("startPostCode", searchSuburbReq.getStartPostCode());
			errorMetaData.put("endPostCode", searchSuburbReq.getEndPostCode());
			suburbErrors.add(SuburbError.prepareCloudDatalakeError(HttpStatus.BAD_REQUEST, "SUB_ERR_004",
					"Post code range must have both start and end post codes.", errorMetaData));
			throw new SuburbBusinessException(suburbErrors);
		}
	}

}
