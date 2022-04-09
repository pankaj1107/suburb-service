package com.example.suburbs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.suburbs.entity.SuburbDetails;
import com.example.suburbs.exception.SuburbBusinessException;
import com.example.suburbs.exception.SuburbError;
import com.example.suburbs.model.SearchSuburbRequest;
import com.example.suburbs.model.SearchSuburbResponse;
import com.example.suburbs.repository.SuburbRepository;

import lombok.RequiredArgsConstructor;

/**
 * Suburb service implementation
 * 
 * @author pankaj.yadav
 *
 */
@Service
@RequiredArgsConstructor
public class SuburbService implements ISuburbService {
	
	/** The suburb repository. */
	private final SuburbRepository suburbRepo;

	@Override
	public List<SuburbDetails> saveSuburbDetails(final List<SuburbDetails> suburbDetails) {
		return suburbRepo.saveAll(suburbDetails);
	}

	@Override
	public SearchSuburbResponse findSuburbDetails(final SearchSuburbRequest searchSuburbReq) {
		final SearchSuburbResponse searchSuburbResp = new SearchSuburbResponse();
		final List<SuburbDetails> suburbDetailsInDB = suburbRepo.findSuburbDetailsOnPostCodeRange
				(searchSuburbReq.getStartPostCode(), searchSuburbReq.getEndPostCode());
		if (!CollectionUtils.isEmpty(suburbDetailsInDB)) {
			searchSuburbResp.setSuburbDetails(suburbDetailsInDB);
			final List<Long> totalCharCount = new ArrayList<>();
			suburbDetailsInDB.stream().forEach(suburbInfo -> {
				totalCharCount.add(suburbInfo.getSuburbName().chars().count());
			});
			searchSuburbResp.setTotalSuburbNameCharsCount(totalCharCount.stream().reduce(0l, Long::sum));
		} else {
			final List<SuburbError> suburbErrors = new ArrayList<>();
			final Map<String, Object> errorMetaData = new HashMap<>();
			errorMetaData.put("startPostCode", searchSuburbReq.getStartPostCode());
			errorMetaData.put("endPostCode", searchSuburbReq.getEndPostCode());
			suburbErrors.add(SuburbError.prepareCloudDatalakeError(HttpStatus.NOT_FOUND, "SUB_ERR_007",
					"No records found with input search criteria.", errorMetaData));
			throw new SuburbBusinessException(suburbErrors);
		}
		return searchSuburbResp;
	}

}
