package com.customexception;

/** 
* This class is for Custom Employee Exception
* Extends Exception 
*/
public class EmployeeException extends Exception {
    public EmployeeException(String message, Throwable throwable) {
	super(message, throwable);
    }
}