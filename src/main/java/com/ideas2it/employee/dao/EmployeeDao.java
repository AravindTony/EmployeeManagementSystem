package com.ideas2it.employee.dao;

import java.util.List;

import com.ideas2it.model.Employee;

import com.ideas2it.customexception.EmployeeException;

/** 
 * <p> 
 *This interface has Abstract Methods handles all the Database Operations like
 * Insertion, Display the Records, Update and Delete the Records
 * </p>
 * @author Aravind
 */
public interface EmployeeDao {
    /** 
     * <p>
     * This method return the employee Records to the 
     * Service for the Controller
     * </p>
     * @return employeeRecords - Employee Records List
     * 
     * @throws EmployeeException while get all the Records
     */
    List<Employee> getRecords() throws EmployeeException;

    /** 
     * <p>
     * This method insert the Data to the Database
     * </p>
     * @param employee - Employee as Object
     * 
     * @return boolean value - Inserted Successfully or not.
     *
     * @throws EmployeeException while inserting the data to the Database
     */
    Employee insertData(Employee employee) throws EmployeeException;

    /** 
     * <p>
     * This method update the Data in the Employee List
     * </p>
     * @param employee - Employee as Object
     * @throws EmployeeException while Updating the Record
     */
    void updateRecord(Employee employee) throws EmployeeException;
 
    /**
     * <p>
     * This Method to delete an Employee Record
     * by using the Employee Id in the Database
     * </p>
     * @param deleteId - EmployeeId to Delete
     *
     * @throws EmployeeException while Deleting the Record
     */
    void deleteRecord(int deleteId) throws EmployeeException;

    /**
     * <p>
     * This method return the Employee by id
     * </p>
     * @param employeeId - ID of the Employee
     * 
     * @return Employee - as the Object
     * 
     * @throws EmployeeException - While getting Employee by id
     */
    Employee getEmployeeById(int employeeId) throws EmployeeException;
}