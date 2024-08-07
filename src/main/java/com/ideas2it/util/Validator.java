package com.ideas2it.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * <p>
 * This class handles all the validations of the Employee like 
 * validate Name, validate Email Id, and Mobile Number
 * </p>
 * @author Aravind
 */
public class Validator {
    /** 
     * <p>
     * This method to validate the email 
     * address get by the user
     * 
     * @param employeeEmail is to validate
     * @return Boolean Value for Valid Email
     * </p>
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
     * <p>
     * This method to validate the Name 
     * get by the user
     *
     * @param employeeName - Name of the Employee to Validate
     * @return boolean value if the name valid or not
     * </p>
     */
    public static boolean isValidName(String employeeName) {
        return employeeName.matches("[a-zA-Z\\s]+");
    }

    /** 
     * <p>
     * This method is used to validate Mobile Number
     *
     * @param mobileNumber - Employee Mobile Number to Validate
     * @return boolean value if the Mobile Number valid or not
     * </p>
     */
    public static boolean validateMobile(long mobileNumber) {
        return Long.toString(mobileNumber).length() == 10;
    }
}