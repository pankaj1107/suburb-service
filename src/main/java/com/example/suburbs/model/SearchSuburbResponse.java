package com.example.suburbs.model;

import java.util.List;

import com.example.suburbs.entity.SuburbDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Search suburb response.
 * 
 * @author pankaj.yadav
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchSuburbResponse {
	
	/** The suburb details. */
	@ApiModelProperty(name = "suburbDetails", value = "The values array which contains suburb details.")
	private List<SuburbDetails> suburbDetails;
	
	@ApiModelProperty(dataType = "java.lang.Long", example = "29", value = "It holds total no. of characters for suburb names.")
	private Long totalSuburbNameCharsCount;

}
