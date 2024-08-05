package com.department.controller;

import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.service.EmployeeServiceImpl;
import com.employee.service.EmployeeService;
import com.model.Employee;
import com.model.Department;
import com.customexception.EmployeeException;

/**
* <p>This class is the Controller of Department for
* Add departments to the Repository 
* Display list of the departments in the Repository
* Delete Departments in the Repository</p>
* @author Aravind
*/
public class DepartmentController {
    DepartmentService departmentService = new DepartmentServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();
    Scanner inputObject = new Scanner(System.in);

    /** 
    * This method add department to Department
    * Warehouse
    */
    public void addDepartment() {
	String departmentName = "";
        try {
	    System.out.println("Enter Department to Add:");
	    departmentName = inputObject.nextLine();
	    departmentService.addEmployeeDepartment(departmentName);
	    System.out.println("Department Added..");
        }
	catch (EmployeeException e) {
	    System.out.println("Unable to Add Department to Department Repository.." 
			      + departmentName + e.getMessage());
        }
    }

    /** 
    * This method to delete a department 
    * in the Department Warehouse
    */
    public void deleteDepartment() {
	int departmentId = 0;
	try {
	    for(Map.Entry<Integer, Department> e : 
                            departmentService.getDepartments().entrySet()) {
                System.out.println(e.getKey() + "." + e.getValue().getDepartmentName());
            }
	    System.out.println("Enter Department Id to Delete : ");
	    departmentId = inputObject.nextInt();
	    departmentService.deleteDepartment(departmentId);
        }
	catch (EmployeeException e) {
	    System.out.println("Unable to delete This Department :" + departmentId + e.getMessage());
        }
    }

    /**
    * This method display the departments in the department 
    * Warehosuse of Repository
    */
    public int listDepartments() {
        int chosenDepartment = 0;
        try {
            Map<Integer, Department> employeeDepartments = departmentService.getDepartments();
            boolean isValidInput = false;
            while(!isValidInput) {
                System.out.print("Enter choice : ");
                int userUpdationChoice = inputObject.nextInt();
                isValidInput = true;   
                if (employeeDepartments.containsKey(userUpdationChoice)) {
                    chosenDepartment = userUpdationChoice;
                } else {
                    System.out.println("Enter Valid option...!");
                    return listDepartments();
                }
	    }
        } catch (EmployeeException e) {
            System.out.println("Unable to List Department List..");
            inputObject.next();
        }
        return chosenDepartment;
    }

    /** This method gets department Id from the user
    * and return the id
    */
    public int readDepartmentId() {
        departmentService.printDepartments();
        System.out.print("Enter Department Id: ");
        int departmentInputId = inputObject.nextInt();
	return departmentInputId;
    }
 
    /** 
    * This method get the Department from the 
    * user.
    */
    public int readDepartment() {
        System.out.println("Enter Department : ");
        int departmentId = listDepartments();
        return departmentId;
    } 

    /** 
    * This method display the employees in the
    * specific Department
    */
    public void displayDepartmentEmployees() {
 	try {
	    int departmentIdValue = readDepartmentId();
	    Department department = departmentService.getDepartmentObject(departmentIdValue);
	    String format = "| %-4s | %-15s | %-10s | %-5s | %-15s | %-15s" 
                                            + "| %-7s | %-5s \n";
            System.out.format(format, "Id", "Name", "Department", "Age"
		     	     , "Mobile Number", "Email Id"
                             , "Qualification", "Experience");
            System.out.println("---------------------------------------"
	 		      + "--------------------------------------"
			      + "--------------------------------------");
            List<Employee> employees = new ArrayList<>(department.getEmployees());
	    for (Employee employee : employees) {
                if (employee.isDeleted == false) {
		    System.out.format(format, employee.getEmployeeId()
		     	         , employee.getEmployeeName()
			         , employee.getDepartment().getDepartmentName()
			         , employee.getAge()
		                 , employee.getMobileNumber()
			         , employee.getEmployeeEmail()
			         , employee.getQualification()
			         , employee.getExperience());
	            System.out.println("---------------------------------------"
			         + "--------------------------------------"
			         + "--------------------------------------"); 
		}
            }
        } catch (EmployeeException e) {
	    System.out.println("An Error Occured while Display Department Employees.." + e.getMessage());
        }
    }

    /**
    * This method to Update an Department Name with id
    */
    public void updateDepartment() {
	try {
	    System.out.println("Enter Department Id to Update :");
	    int departmentId = inputObject.nextInt();
	    inputObject.nextLine();
            System.out.println("Enter Department Name to Update:");
	    String departmentName = inputObject.nextLine();
	    Department department = departmentService.getDepartmentObject(departmentId);
	    department.setDepartmentName(departmentName);	
	    departmentService.updateDepartmentRecord(department);
	} catch (EmployeeException e) {
	    System.out.println("Unable to Update Department Name :" + e.getMessage());
        }
    }
}