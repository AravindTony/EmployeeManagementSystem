package com.ideas2it.department.service;

import java.util.Map;
import java.util.List;

import com.ideas2it.customexception.EmployeeException;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;

/**
* <p>
* This interface has Abstract methods to implements the 
* methods like Department Operations 
* </p>
* @author Aravind
*/
public interface DepartmentService {
 
    /** 
     * <p>
     * This method Return the Employee Departments to the Controller
     * </p>
     * @return Departments as the Map objects
     * 
     * @throws EmployeeException while get Departments
     */
    Map<Integer, Department> getDepartments() throws EmployeeException;

    /** 
     * <p>
     * This method return the Employee Records to the Employee Controller
     * </p>
     * @return Employees - List of Employees
     */
    List<Employee> getEmployeeRecords();

    /** 
     * <p>
     * This method passes department object as argument to  
     * department Warehouse or Dao 
     * </p>
     * @param departmentName - Name of the Department
     *
     * @throws EmployeeException - Error while Adding Department to the Database
     */
    void addDepartment(String departmentName) throws EmployeeException;
   
    /**
     * <p>
     * This method to delete a department by the department
     * id given by the user
     * </p>
     * @param id - Department ID given by the user
     *
     * @throws EmployeeException - Error while Delete Department by Department Id
     */
    void deleteDepartment(int id) throws EmployeeException;

    /**  
     * <p>
     * This method return the Department Object 
     * with the Department Id
     * </p>
     * @param departmentId - ID of the Department to get Department Object
     *
     * @return Department - Department as the Object
     * 
     * @throws EmployeeException- Error while get Department as Object by Department ID
     */
    Department getDepartmentObject(int departmentId) throws EmployeeException;

    /**
     * <p>
     * This method update Department name with id
     * </p>
     * @param department - Department as the Object
     * 
     * @throws EmployeeException - Error while Update Department
     */
    void updateDepartmentRecord(Department department) throws EmployeeException;
}