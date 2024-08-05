package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.time.Period;

import com.employee.dao.EmployeeDao;
import com.department.service.DepartmentService;
import com.model.Employee;
import com.customexception.EmployeeException;
import com.model.Department;
import com.department.service.DepartmentServiceImpl;
import com.employee.dao.EmployeeDaoImpl;
import com.model.SalaryAccount;
import com.salaryaccount.service.SalaryAccountService;
import com.salaryaccount.service.SalaryAccountServiceImpl;

/**
 * <p>
 *This Class is the service class for the Employee Controller
 * and Dao for the Business Logic
 * </p>
 * @author Aravind
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();
    SalaryAccountService salaryAccountService = new SalaryAccountServiceImpl();

    @Override
    public List<Employee> getEmployeeDetails() throws EmployeeException {
	return employeeDao.getRecords();
    }

    @Override
    public Department getDepartmentObj(int departmentId) throws EmployeeException {
	return departmentService.getDepartmentObject(departmentId);
    }

    @Override
    public Employee addData(String employeeName, LocalDate dateOfBirth, long mobile, int departmentId, String qualification, 
			int experience, String employeeEmail, String bankName, long accountNumber) throws EmployeeException {
        Department department = getDepartmentObj(departmentId);
	SalaryAccount account = new SalaryAccount(bankName, accountNumber);
	salaryAccountService.addSalaryAccount(account);
	Employee employee = new Employee(employeeName, department, dateOfBirth, mobile, employeeEmail, qualification, experience, account);
        return employeeDao.insertData(employee);
    }

    @Override
    public void updateRecord(Employee employee) throws EmployeeException {
	employeeDao.updateRecord(employee);
    }

    @Override
    public void deleteRecord(int deleteId) throws EmployeeException {
        employeeDao.deleteRecord(deleteId);
    }
     
    @Override
    public void getDepartments() throws EmployeeException {
	departmentService.getDepartments();
    }

    @Override
    public Map<Integer, Department> getEmployeeDepartments() throws EmployeeException {
	return departmentService.getDepartments();
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeException {
        return employeeDao.getEmployeeById(id);
    }
}