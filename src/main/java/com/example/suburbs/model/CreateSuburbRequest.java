package com.example.suburbs.model;

import java.util.List;

import com.example.suburbs.entity.SuburbDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Create suburb request.
 * 
 * @author pankaj.yadav
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateSuburbRequest {
	
	/** The suburb details. */
	@ApiModelProperty(name = "suburbDetails", value = "The values array which contains suburb details.")
	private List<SuburbDetails> suburbDetails;

}
