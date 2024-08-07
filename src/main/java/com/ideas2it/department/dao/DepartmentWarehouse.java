package com.ideas2it.department.dao;

import java.util.Map;

import com.ideas2it.customException.EmployeeException;
import com.ideas2it.model.Department;

/**
 * <p>
 * This Interface for Department handles the Operations
 * like Insertion, Retrieving for the Department Information
 * From the Database. 
 * </p>
 * @author Aravind
 */
public interface DepartmentWarehouse {
 
    /** 
     * <p>
     * This method Add the department to the 
     * Map Interface
     * </p>
     * @param departmentName is the Name of Department
     *
     * @throws EmployeeException while Inserting the Department
     */
    void insertDepartment(String departmentName) throws EmployeeException;

    /** 
     * <p>
     * This method return the Departments from the Database
     * as Map Object
     * </p>
     * @return Department as Object
     *
     * @throws EmployeeException while get Departments 
     */
    Map<Integer, Department> getEmployeeDepartments() throws EmployeeException;

    /** 
     * <p>
     * This method display the specific Department using 
     * the id given by user..
     * </p>
     * @param departmentId - used to get Particular Department
     * 
     * @return Department Object by departmentId
     * 
     * @throws EmployeeException while get Department Object with Department id
     */
    Department getDepartmentObject(int departmentId) throws EmployeeException;

    /**
     * <p>
     * This method Delete the Department with the Department Id
     * </p>
     * @param departmentId - ID of the Department
     * 
     * @throws EmployeeException while delete the Department with the Department id
     */
    void deleteDepartment(int departmentId) throws EmployeeException;

    /**
     * <p>
     * This method Update the Department Name with Department Object
     * </p>
     * @param department - Department as the Object
     */
    void updateDepartmentName(Department department) throws EmployeeException;
}