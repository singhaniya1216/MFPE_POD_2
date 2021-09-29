package com.cognizant.medicinestock.exception;

/**
 * This is a User defined exception class which throws an exception when a
 * medicine is not found in the database.
 */
public class MedicineNotFoundException extends Exception {

	private static final long serialVersionUID = 6878345868618838621L;

	public MedicineNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
