package com.employee.service;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import com.customexception.EmployeeException;
import com.model.Mentor;
import com.model.Employee;
import com.model.Department;

public interface EmployeeService {
 
    /** 
    * This method return the Employee Records from the Database to 
    * the Controller
    */
    public List<Employee> getEmployeeDetails() throws EmployeeException;
 
    /**
    * This method get the Department Object using the Id
    * 
    * @param departmentId - Id of the Department
    * @return Department Object by the Id
    */
    public Department getDepartmentObj(int departmentId) throws EmployeeException;

    /** 
    * This method send the Data to the insertData method 
    * to insert the data to Dao
    * 
    * @return Employee as the Object
    *
    * @throws Employee Exception while adding the Data to the Database
    */
    public Employee addData(String employeeName, LocalDate dateOfBirth, long mobile, int departmentId, String qualification, 
			int experience, String employeeEmail, String bankName, long accountNumber) throws EmployeeException;

    /**
    * This method update the records in the Employee List based
    * on the User Choice
    * 
    * @throws Employee Exception while Update the Data in the Database
    */
    public void updateRecord(Employee employee) throws EmployeeException;

    /**
    * This method to delete an Employee Record
    * by using the Employee Id
    *
    * @param deleteId is used to match the id from the List 
    *
    * @throws Employee Exception while Delete the Data in the Database
    */ 
    public void deleteRecord(int deleteId) throws EmployeeException;

    /**
    * This method to display the Departments in the 
    * Department Repository
    */
    public void getDepartments();

    /**
    * This method return the Employee Departments
    * 
    * @return Employee Departments
    */
    public Map<Integer, Department> getEmployeeDepartments() throws EmployeeException;

    /** 
    * This method return the Employee Object by the id
    * 
    * @return Employee Record
    *
    * @throws Employee Exception while getting Employee by Id
    */
    public Employee getEmployeeById(int id) throws EmployeeException;

}