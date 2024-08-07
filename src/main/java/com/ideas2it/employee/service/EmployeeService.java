package com.ideas2it.employee.service;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import com.ideas2it.customexception.EmployeeException;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Department;

/**
 * <p>
 * This interface has Abstract methods for Employee Service
 * </p>
 */
public interface EmployeeService {
 
    /** 
     * <p>
     * This method return the Employee Records from the Database to 
     * the Controller
     * </p>
     *
     * @return Employee - List of Employees
     */
    List<Employee> getEmployeeDetails() throws EmployeeException;
 
    /**
     * <p>
     * This method get the Department Object using the Id
     * </p>
     * @param departmentId - ID of the Department
     *
     * @return Department Object by the ID
     */
    Department getDepartmentObj(int departmentId) throws EmployeeException;

    /** 
     * <p>
     * This method send the Data to the insertData method 
     * to insert the data to Dao
     * </p>
     * @return Employee as the Object
     *
     * @throws EmployeeException while adding the Data to the Database
     */
    Employee addData(String employeeName, LocalDate dateOfBirth, long mobile, int departmentId, String qualification,
			int experience, String employeeEmail, String bankName, long accountNumber) throws EmployeeException;

    /**
     * <p>
     * This method update the records in the Employee List based
     * on the User Choice
     * </p>
     * @throws EmployeeException while Update the Data in the Database
     */
    void updateRecord(Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method to delete an Employee Record
     * by using the Employee Id
     * </p>
     * @param deleteId is used to match the id from the List 
     *
     * @throws EmployeeException while Delete the Data in the Database
     */ 
    void deleteRecord(int deleteId) throws EmployeeException;

    /**
     * <p>
     * This method to display the Departments in the 
     * Department Repository
     * </p>
     */
    void getDepartments() throws EmployeeException;

    /**
     * <p>
     * This method return the Employee Departments
     * </p>
     * @return Employee Departments
     */
    Map<Integer, Department> getEmployeeDepartments() throws EmployeeException;

    /** 
     * <p>
     * This method return the Employee Object by the id
     * </p>
     * @return Employee Record
     *
     * @throws EmployeeException while getting Employee by ID
     */
    Employee getEmployeeById(int id) throws EmployeeException;
}