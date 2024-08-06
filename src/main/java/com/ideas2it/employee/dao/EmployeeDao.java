package com.ideas2it.employee.dao;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Mentor;
import com.ideas2it.model.Department;
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
    public List<Employee> getRecords() throws EmployeeException;

    /** 
     * <p>
     * This method insert the Data to the Database
     * </p>
     * @param employeeName - Name of the Employee
     * @param mobile - Mobile Number of the Employee
     * @param departmentId - Id of the Department
     * @param qualification - Qualification of the Employee
     * @param experience - Total Experience of the Employee
     * @param employeeEmail - Email id of the Employee
     * @param departmentName - Name of the Department as the Object
     * 
     * @return boolean value - Inserted Successfully or not..
     *
     * @throws EmployeeException while inserting the data to the Database
     */
    public Employee insertData(Employee employee) throws EmployeeException;

    /** 
     * <p>
     * This method update the Data in the Employee List
     * </p>
     * @param updateId - Updated id of the Employee
     * @param updateChoice - User Entered Choice to Update Record
     * @param newName - Updated Name of the Employee
     * @param newMobileNumber - Updated Mobile Number of the Employee
     * @param updatedEmail - Updated Email of the Employee
     * @param qualificaton - Updated Qualification of the Employee
     * @param experience - Updated Experience of the Employee
     * @param updatedDateOfBirth - Updated Date of birth of the Employee

     * @throws EmployeeException while Updating the Record
     */
    public void updateRecord(Employee employee) throws EmployeeException;
 
    /**
     * <p>
     * This Method to delete an Employee Record
     * by using the Employee Id in the Database
     * </p>
     * @param deleteId - EmployeeId to Delete
     *
     * @throws EmployeeException while Deleting the Record
     */
    public void deleteRecord(int deleteId) throws EmployeeException;

    /**
     * <p>
     * This method return the Employee by id
     * </p>
     * @param employeeId - Id of the Employee
     * 
     * @return Employee - as the Object
     * 
     * @throws EmployeeException - While getting Employee by id
     */
    public Employee getEmployeeById(int employeeId) throws EmployeeException;
}