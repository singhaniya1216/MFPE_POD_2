package com.cognizant.pharmacysupply.exception;

/**
 * This is user defined exception class which handles the exception thrown when
 * medicine is not found in the database.
 */
public class MedicineNotFoundException extends Exception {

	private static final long serialVersionUID = 6878345868618838621L;

	public MedicineNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
