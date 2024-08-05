package com.department.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.customexception.EmployeeException;
import com.model.Department;

/**
* <p>This Interface for Department handles the Operations
* like Insertion, Retrieving for the Department Information
* From the Database. </p>
* @author Aravind
*/
public interface DepartmentWarehouse {
 
    /** This method Add the department to the 
    * Map Interface
    * 
    * @param departmentName is the Name of Department
    *
    * @throws Employee Exception while Inserting the Department
    */
    public void insertDepartment(String departmentName) throws EmployeeException;

    /**
    * This method return the Departments from the Database
    * as Map Object
    *
    * @return Department as Object
    *
    * @throws Employee Exception while get Employee Departments 
    */
    
    public Map<Integer, Department> getEmployeeDepartments() throws EmployeeException;

    /** 
    * This method display the specific Department using 
    * the id given by user..
    * 
    * @param departmentId department Id used to get Particular Department
    * 
    * @return Department Object by department Id
    * 
    * @throws EmployeeException while get Department Object with Department id
    */
    public Department getDepartmentObject(int departmentId) throws EmployeeException;


    /**
    * This method Delete the Department with the Department Id
    * 
    * @param departmentId - Id of the Department
    * 
    * @throws EmployeeException while delete the Department with the Department id
    */
    public void deleteDepartment(int departmentId) throws EmployeeException;

    /**
    * This method Update the Department Name with Department Object
    *
    * @param Department - Department as the Object
    */
    public void updateDepartmentName(Department department) throws EmployeeException;
}