package com.example.suburbs.constant;

/**
 * Hold constant variables.
 * 
 * @author pankaj.yadav
 *
 */
public final class SuburbConstant {
	
	/**
     * Instantiates a new constant.
     */
    private SuburbConstant() {

    }
    
    /** Holds description for create suburbs API operation. */
    public static final String CRT_SUBURB_API_OP_DESC = "Create suburb details with name and post codes.";
    
    /** Holds notes for create suburbs API operation. */
    public static final String CRT_SUBURB_API_OP_NOTE = " This API persists all the provided suburb details.";
    
    /** Holds description for find suburbs API operation. */
    public static final String FIND_SUBURB_API_OP_DESC = "Search suburb details post codes range.";
    
    /** Holds notes for find suburbs API operation. */
    public static final String FIND_SUBURB_API_OP_NOTE = " This API retrieves all the suburb details on the basis of provided post codes range.";
    
    /** Holds HTTP status code for CREATED. */
    public static final int HTTP_STATUS_CREATED = 201;
    
    /** Holds HTTP status message for CREATED. */
    public static final String HTTP_STATUS_CREATED_MSSG = "Successfully created.";
    
    /** Holds HTTP status code for BAD_REQUEST. */
    public static final int HTTP_STATUS_BAD_REQ = 400;
    
    /** Holds HTTP status message for BAD_REQUEST. */
    public static final String HTTP_STATUS_BAD_REQ_MSSG = "Invalid request body.";
    
    /** Holds HTTP status code for INTERNAL_SERVER_ERROR. */
    public static final int HTTP_STATUS_INT_SERVER_ERR = 500;
    
    /** Holds HTTP status message for INTERNAL_SERVER_ERROR. */
    public static final String HTTP_STATUS_INT_SERVER_ERR_MSSG = "Invalid request body.";
    
    /** Holds HTTP status code for NOT_FOUND. */
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    
    /** Holds HTTP status message for NOT_FOUND. */
    public static final String HTTP_STATUS_NOT_FOUND_MSSG = "Resource not found.";
    
    /** Holds API context path for suburbs. */
    public static final String SUBURB_API_PATH = "/suburbs";
    
   

}
