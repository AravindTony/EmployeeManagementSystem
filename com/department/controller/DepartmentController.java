package com.department.controller;

import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.service.EmployeeServiceImpl;
import com.employee.service.EmployeeService;
import com.model.Employee;
import com.model.Department;
import com.customexception.EmployeeException;

/**
 *<p>
 *This class is the Controller of Department for
 * Add departments to the Repository 
 * Display list of the departments in the Repository
 * Delete Departments in the Repository
 *</p>
 * @author Aravind
 */
public class DepartmentController {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private static Logger logger = LogManager.getLogger();
    private Scanner inputObject = new Scanner(System.in);

    /** 
     * <p>
     * This method add department to Department
     * Department Database
     * </p>
     */
    public void addDepartment() {
	String departmentName = "";
        try {
	    System.out.println("Enter Department to Add:");
	    departmentName = inputObject.nextLine();
	    departmentService.addDepartment(departmentName);
	    logger.info(departmentName + " Department Added..");
        }
	catch (EmployeeException e) {
	    logger.error("Unable to Add Department to Department Repository.." 
			      + departmentName + e.getMessage());
        }
    }

    /** 
     * <p>
     * This method to delete a department 
     * in the Department Database
     * </p>
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
	    logger.info("Department Deleted with This id : " + departmentId);
        }
	catch (EmployeeException e) {
	    logger.error("Unable to delete This Department :" + departmentId + e.getMessage());
        }
    }

    /**
     * <p>
     * This method display the departments in the
     * Department Database
     * </p>
     * 
     * @return Department Choice get from the user
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
            logger.error("Unable to List Department List..");
            inputObject.next();
        }
        return chosenDepartment;
    }

    /** 
     * <p>
     * This method gets department Id from the user
     * and return the id
     * </p>
     * 
     * @return Department id get from the user
     */
    public int readDepartmentId() {
        int departmentInputId = 0;
        try {
	    for(Map.Entry<Integer, Department> e : 
                    departmentService.getDepartments().entrySet()) {
                System.out.println(e.getKey() + "." + e.getValue().getDepartmentName());
            }
            System.out.print("Enter Department Id: ");
            departmentInputId = inputObject.nextInt();
        } catch (EmployeeException e) {
	    logger.error("Unable to Read Department Id..");
        }
	return departmentInputId;
    }
 
    /** 
     * <p>
     * This method get the Department from the 
     * user.
     * </p>
     * 
     * @return Department Id
     */
    public int readDepartment() {
        System.out.println("Enter Department : ");
        int departmentId = listDepartments();
        return departmentId;
    } 

    /** 
     * <p>
     * This method display the employees in the
     * specific Department
     * </p>
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
	    logger.error("An Error Occured while Display Department Employees.." + e.getMessage());
        }
    }

    /**
     * <p>
     * This method to Update an Department Name with id
     * </p>
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
	    logger.error("Unable to Update Department Name :" + e.getMessage());
        }
    }
}