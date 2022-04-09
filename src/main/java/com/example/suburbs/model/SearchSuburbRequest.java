package com.example.suburbs.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Search suburb request.
 * 
 * @author pankaj.yadav
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchSuburbRequest {
	
	/** The start post code. */
	@ApiModelProperty(dataType = "java.lang.Integer", example = "110021", value = "It holds the start postal code.")
	private Integer startPostCode;
	
	/** The end post code. */
	@ApiModelProperty(dataType = "java.lang.Integer", example = "110099", value = "It holds the end postal code.")
	private Integer endPostCode;
	
}
