package com.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.employee.controller.EmployeeController;

/**
* <p>This class handles all the validations of the Employee like 
* validate Name, validate Email Id, and Mobile Number</p>
* @author Aravind
*/
public class Validator {
    /** 
    * emailValidator is used to validate the email 
    * address get by the user
    *
    * @param employeeEmail is to validate
    */
    public static boolean emailValidator(String employeeEmail) {
       	try {
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(employeeEmail);
            employeeEmail = employeeEmail.toLowerCase();
            return matcher.matches();
        } catch (Exception e) {
            System.out.println("An error occurred while validating the email address: " 
                              + e.getMessage());
        }
        return false;
    }

    /** 
    * This method to validate the Name 
    * get by the user
    */
    public static boolean isValidName(String employeeName) {
        try {
            if (employeeName.matches("[a-zA-Z\\s]+")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while validating the name: " 
			      + e.getMessage());
        }
        return false;
    }

    /** 
    * This method is used to validate Mobile Number
    */
    public static boolean validateMobile(long mobileNumber) {
	try {
            if(Long.toString(mobileNumber).length() == 10) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while validating the mobile number: " 
			      + e.getMessage());
        }
	return false;
    }
}