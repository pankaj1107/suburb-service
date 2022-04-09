package com.example.suburbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.suburbs.entity.SuburbDetails;

/**
 * Suburb Repository
 * 
 * @author pankaj.yadav
 *
 */
@Repository
public interface SuburbRepository extends JpaRepository<SuburbDetails, Long> {
	
	/**
	 * Find suburb details on the basis of provided post code range.
	 * 
	 * @param startPostCode 
	 * 		start post code
	 * @param endPostCode 
	 * 		end post code
	 * @return suburb details
	 */
	@Query("from SuburbDetails subInfo where subInfo.postCode between :startPostCode and :endPostCode order by subInfo.suburbName asc")
	List<SuburbDetails> findSuburbDetailsOnPostCodeRange(@Param("startPostCode") final Integer startPostCode, 
			@Param("endPostCode") final Integer endPostCode);
	
	

}
