package com.example.suburbs.resource;

import static com.example.suburbs.constant.SuburbConstant.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.suburbs.entity.SuburbDetails;
import com.example.suburbs.exception.SuburbBusinessException;
import com.example.suburbs.exception.SuburbError;
import com.example.suburbs.model.CreateSuburbRequest;
import com.example.suburbs.model.SearchSuburbRequest;
import com.example.suburbs.model.SearchSuburbResponse;
import com.example.suburbs.service.ISuburbService;
import com.example.suburbs.validator.SuburbValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Suburb REST API resource.
 * 
 * @author pankaj.yadav
 *
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class SuburbResource {
	
	/** The suburb service. */
	private final ISuburbService iSuburbService;
	
	/** The suburb validator. */
	private final SuburbValidator suburbValidator;
	
	/**
	 * Save suburb details.
	 * 
	 * @param createSuburbReq 
	 * 		create suburb request {@link CreateSuburbRequest}
	 * @return suburb details
	 */
	@ApiOperation(value = CRT_SUBURB_API_OP_DESC, notes = CRT_SUBURB_API_OP_NOTE, response = SuburbDetails.class)
	@ApiResponses(value = { @ApiResponse(code = HTTP_STATUS_CREATED, message = HTTP_STATUS_CREATED_MSSG),
			@ApiResponse(code = HTTP_STATUS_BAD_REQ, message = HTTP_STATUS_BAD_REQ_MSSG),
			@ApiResponse(code = HTTP_STATUS_INT_SERVER_ERR, message = HTTP_STATUS_INT_SERVER_ERR_MSSG)})
	@PostMapping(SUBURB_API_PATH)
	public ResponseEntity<List<SuburbDetails>> saveSuburbDetails(@RequestBody CreateSuburbRequest createSuburbReq) {
		log.debug("*** Create suburb details ***");
		try {
			final List<SuburbDetails> suburbDetailsInDB = iSuburbService.saveSuburbDetails(createSuburbReq.getSuburbDetails());
			return new ResponseEntity<List<SuburbDetails>>(suburbDetailsInDB, HttpStatus.CREATED);
		} catch(final Exception ex) {
			final List<SuburbError> suburbErrors = new ArrayList<>();
			suburbErrors.add(SuburbError.prepareCloudDatalakeError(HttpStatus.INTERNAL_SERVER_ERROR, "SUB_ERR_001",
					"Error occurred while trying to save suburb details.", null));
			throw new SuburbBusinessException(suburbErrors);
		}
	}
	
	/**
	 * Find suburb details on the basis of provided post codes range.
	 * 
	 * @param searchSuburbReq 
	 * 		search suburb request {@link SearchSuburbRequest}
	 * @return suburb details
	 */
	@ApiOperation(value = FIND_SUBURB_API_OP_DESC, notes = FIND_SUBURB_API_OP_NOTE, response = SuburbDetails.class)
	@ApiResponses(value = { @ApiResponse(code = HTTP_STATUS_CREATED, message = HTTP_STATUS_BAD_REQ_MSSG),
			@ApiResponse(code = HTTP_STATUS_BAD_REQ, message = HTTP_STATUS_INT_SERVER_ERR_MSSG),
			@ApiResponse(code = HTTP_STATUS_INT_SERVER_ERR, message = HTTP_STATUS_NOT_FOUND_MSSG) })
	@GetMapping(SUBURB_API_PATH)
	public ResponseEntity<SearchSuburbResponse> findSuburbDetails(@RequestBody SearchSuburbRequest searchSuburbReq) {
		suburbValidator.validateSearchSuburbReq(searchSuburbReq);
		log.info("Search suburb details with startPostCode :: {} and endPostCode :: {}",
				searchSuburbReq.getStartPostCode(), searchSuburbReq.getEndPostCode());
		try {
			final SearchSuburbResponse searchSuburbResp = iSuburbService.findSuburbDetails(searchSuburbReq);
			return new ResponseEntity<SearchSuburbResponse>(searchSuburbResp, HttpStatus.FOUND);
		} catch(final Exception ex) {
			final List<SuburbError> suburbErrors = new ArrayList<>();
			final Map<String, Object> errorMetaData = new HashMap<>();
			errorMetaData.put("startPostCode", searchSuburbReq.getStartPostCode());
			errorMetaData.put("endPostCode", searchSuburbReq.getEndPostCode());
			suburbErrors.add(SuburbError.prepareCloudDatalakeError(HttpStatus.INTERNAL_SERVER_ERROR, "SUB_ERR_002",
					"Error occurred while trying to find suburb details.", errorMetaData));
			throw new SuburbBusinessException(suburbErrors);
		}
	}

}
