package com.example.suburbs.service;

import java.util.List;

import com.example.suburbs.entity.SuburbDetails;
import com.example.suburbs.model.SearchSuburbRequest;
import com.example.suburbs.model.SearchSuburbResponse;

/**
 * Suburb Service.
 * 
 * @author pankaj.yadav
 *
 */
public interface ISuburbService {

	/**
	 * Save suburb details.
	 * 
	 * @param suburbDetails 
	 * 		suburb details to be created {@link SuburbDetails}
	 * @return suburb details {@link SuburbDetails}
	 */
	List<SuburbDetails> saveSuburbDetails(final List<SuburbDetails> suburbDetails);
	
	/**
	 * Find suburb details on the basis of provided post codes range.
	 * 
	 * @param searchSuburbReq 
	 * 		search suburb request {@link SearchSuburbRequest}
	 * @return suburb response
	 */
	SearchSuburbResponse findSuburbDetails(final SearchSuburbRequest searchSuburbReq);

}
