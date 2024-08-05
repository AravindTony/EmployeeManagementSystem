package com.employee.service;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import com.customexception.EmployeeException;
import com.model.Mentor;
import com.model.Employee;
import com.model.Department;

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
    public List<Employee> getEmployeeDetails() throws EmployeeException;
 
    /**
     * <p>
     * This method get the Department Object using the Id
     * </p>
     * @param departmentId - Id of the Department
     *
     * @return Department Object by the Id
     */
    public Department getDepartmentObj(int departmentId) throws EmployeeException;

    /** 
     * <p>
     * This method send the Data to the insertData method 
     * to insert the data to Dao
     * </p>
     * @return Employee as the Object
     *
     * @throws Employee Exception while adding the Data to the Database
     */
    public Employee addData(String employeeName, LocalDate dateOfBirth, long mobile, int departmentId, String qualification, 
			int experience, String employeeEmail, String bankName, long accountNumber) throws EmployeeException;

    /**
     * <p>
     * This method update the records in the Employee List based
     * on the User Choice
     * </p>
     * @throws Employee Exception while Update the Data in the Database
     */
    public void updateRecord(Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method to delete an Employee Record
     * by using the Employee Id
     * </p>
     * @param deleteId is used to match the id from the List 
     *
     * @throws Employee Exception while Delete the Data in the Database
     */ 
    public void deleteRecord(int deleteId) throws EmployeeException;

    /**
     * <p>
     * This method to display the Departments in the 
     * Department Repository
     * </p>
     */
    public void getDepartments() throws EmployeeException;

    /**
     * <p>
     * This method return the Employee Departments
     * </p>
     * @return Employee Departments
     */
    public Map<Integer, Department> getEmployeeDepartments() throws EmployeeException;

    /** 
     * <p>
     * This method return the Employee Object by the id
     * </p>
     * @return Employee Record
     *
     * @throws Employee Exception while getting Employee by Id
     */
    public Employee getEmployeeById(int id) throws EmployeeException;
}