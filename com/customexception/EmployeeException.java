package com.customexception;

/** 
 * <p>
 * This class is for Custom Employee Exception
 * Extends Exception 
 * </p>
 * @author Aravind
 */
public class EmployeeException extends Exception {
    public EmployeeException(String message, Throwable throwable) {
	super(message, throwable);
    }
}