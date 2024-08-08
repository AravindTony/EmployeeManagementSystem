package com.ideas2it.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ideas2it.department.dao.DepartmentWarehouse;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Department;
import com.ideas2it.customException.EmployeeException;
import com.ideas2it.department.dao.DepartmentWarehouseImpl;
	
/**
 * <p>
 * This class is the Service class for Department
 * it send the Data to the Department Repository or Dao
 * </p>
 * @author Aravind
 */
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentWarehouse departmentWarehouse = new DepartmentWarehouseImpl();
    static List<Employee> employeeRecords = new ArrayList<>();

    @Override
    public Map<Integer, Department> getDepartments() throws EmployeeException {
	    return departmentWarehouse.getDepartments();
    }

    @Override
    public void addDepartment(String departmentName) throws EmployeeException {
        departmentWarehouse.insertDepartment(departmentName);
    }
    
    @Override
    public void deleteDepartment(int id) throws EmployeeException {
 	    departmentWarehouse.deleteDepartment(id);
    }

    @Override
    public Department getDepartmentObject(int departmentId) throws EmployeeException {
	    return departmentWarehouse.getDepartmentObject(departmentId);
    }

    @Override
    public void updateDepartmentRecord(Department department) throws EmployeeException {
	    departmentWarehouse.updateDepartmentName(department);
    } 
}