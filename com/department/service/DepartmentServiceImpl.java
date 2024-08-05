package com.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.department.dao.DepartmentWarehouse;
import com.model.Employee;
import com.model.Department;
import com.customexception.EmployeeException;
import com.department.dao.DepartmentWarehouseImpl;
	
/**
* This class is the Service class for Department
* it send the Data to the Department Repository or Dao
* @author Aravind
*/
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentWarehouse departmentWarehouse = new DepartmentWarehouseImpl();
    static List<Employee> employeeRecords = new ArrayList<>();

    /** 
    * This method Return the Employee Departments to the Controller
    */
    public Map<Integer, Department> getDepartments() throws EmployeeException {
	return departmentWarehouse.getEmployeeDepartments();
    }   

    /** 
    * This method return the Employee Records to the Controller
    */
    public List<Employee> getEmployeeRecords() {
        return employeeRecords;
    }

    /**
    * This method for counting the Number of Departments
    * to check departments exist or not
    */
    public Map<Integer, Department> countOfDepartments() throws EmployeeException {
	return departmentWarehouse.getEmployeeDepartments();
    }

    /** 
    * This method passes department object as argument to  
    * department Warehouse or Dao 
    */
    public void addEmployeeDepartment(String departmentName) throws EmployeeException {
        departmentWarehouse.insertDepartment(departmentName);
    }
    
    /**
    * This method to delete a department by the department
    * id given by the user
    *
    * @param id department - Department Id given by the user
    */
    public void deleteDepartment(int id) throws EmployeeException {
        if (departmentWarehouse.getEmployeeDepartments().size() == 0) {
	    System.out.println("No Departments Available to Delete..");
        }
	else {
 	    departmentWarehouse.deleteDepartment(id);
	    System.out.println("Successfully Deleted..");
        }
    }

    /** 
    * This method display the Departments in the 
    * Map Interface
    */
    public void printDepartments() {
        try {
	    for(Map.Entry<Integer, Department> e : 
                    getDepartments().entrySet()) {
                System.out.println(e.getKey() + "." + e.getValue().getDepartmentName());
            }
        } catch (EmployeeException e) {
	    System.out.println("Unable to Print Departments..");
	}
    }

    /** 
    * This method return the Department Object 
    * with the Department Id
    */
    public Department getDepartmentObject(int departmentId) throws EmployeeException {
	return departmentWarehouse.getDepartmentObject(departmentId);
    }

    /**
    * This method update Department name with id
    */
    public void updateDepartmentRecord(Department department) throws EmployeeException {
	departmentWarehouse.updateDepartmentName(department);
    } 
}