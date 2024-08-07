package com.ideas2it.department.controller;

import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.department.service.DepartmentService;
import com.ideas2it.department.service.DepartmentServiceImpl;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Department;
import com.ideas2it.customException.EmployeeException;

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
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private static final Logger logger = LogManager.getLogger();
    private final Scanner inputObject = new Scanner(System.in);

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
            logger.info("{} Department Added..", departmentName);
        }
	catch (EmployeeException e) {
        logger.error("Unable to Add Department to Department Repository..{}{}", departmentName, e.getMessage());
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
        logger.info("Department Deleted with This id : {}", departmentId);
        }
	catch (EmployeeException e) {
        logger.error("Unable to delete This Department :{}{}", departmentId, e.getMessage());
        }
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
                if (!employee.isDeleted) {
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
        logger.error("An Error Occurred while Display Department Employees..{}", e.getMessage());
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
        logger.error("Unable to Update Department Name :{}", e.getMessage());
        }
    }
}