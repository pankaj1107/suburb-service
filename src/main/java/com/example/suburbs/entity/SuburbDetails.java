package com.example.suburbs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Suburb Details Entity.
 * 
 * @author pankaj.yadav
 *
 */
@Entity
@Table(name = "suburb_details")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuburbDetails implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	/** The data lake name. */
	@Column(name = "suburb_name", nullable = false, length = 50)
	@ApiModelProperty(dataType = "java.lang.String", example = "Rajinder Nagar", value = "It holds the suburb name.")
	private String suburbName;
	
	/** The data lake name. */
	@Column(name = "post_code", nullable = false, length = 50)
	@ApiModelProperty(dataType = "java.lang.Integer", example = "110071", value = "It holds the postal codes.")
	private Integer postCode;

}
