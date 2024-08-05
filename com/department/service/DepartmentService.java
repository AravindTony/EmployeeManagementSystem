package com.department.service;

import java.util.Map;
import java.util.List;

import com.customexception.EmployeeException;
import com.model.Department;
import com.model.Employee;

public interface DepartmentService {

    public Map<Integer, Department> getDepartments() throws EmployeeException;

    public List<Employee> getEmployeeRecords();

    public Map<Integer, Department> countOfDepartments() throws EmployeeException;

    public void addEmployeeDepartment(String departmentName) throws EmployeeException;
   
    public void deleteDepartment(int id) throws EmployeeException;

    public void printDepartments();

    public Department getDepartmentObject(int departmentId) throws EmployeeException;

    public void updateDepartmentRecord(Department department) throws EmployeeException;
}