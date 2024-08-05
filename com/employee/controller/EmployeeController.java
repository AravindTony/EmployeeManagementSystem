package com.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;
import com.model.Mentor;
import com.model.Employee;
import com.mentor.service.MentorService;
import com.mentor.controller.MentorController;
import com.model.Department;
import com.model.SalaryAccount;
import com.customexception.EmployeeException;
import com.mentor.service.MentorServiceImpl;
import com.utils.Validator;

/**
* <p>This class is the Controller for Employee get all the Informations
* about the Employee and also various methods like
* Create Employee Record 
* Display the Employee record(s)
* Update the Employee Record
* Delete the Employee Record in the Repository</p>
* @author Aravind
*/
public class EmployeeController {
    static Scanner inputObject = new Scanner(System.in);
    MentorService mentorService = new MentorServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();
    MentorController mentorController = new MentorController();

    /**
    * This method used to get the credentials 
    * by the User to create EmployeeRecord.
    */
    public void createEmployeeRecord() {
	try {
	    if (employeeService.getEmployeeDepartments().size() == 0) {
                System.out.println("Add a department to continue.. Departments are Empty..");
            }
            else {
	        try {
                    System.out.println("Employee Management System..");
	            String employeeName = getValidEmployeeName();
	            LocalDate dateOfBirth = getValidDateOfBirth();
                    employeeService.getDepartments();
		    System.out.println("Enter Department Id:");
	            int departmentId = inputObject.nextInt();
		    long mobileNumber = getValidMobileNumber();
          	    inputObject.nextLine();
	            String employeeEmail = getValidEmailId();
                    System.out.println("Enter Employee Qualification :");
                    String qualification = inputObject.nextLine();
                    System.out.println("Enter Employee Experience in Years:");
                    int experience = inputObject.nextInt();
                    inputObject.nextLine();
	            System.out.println("Enter Employee Account Bank Name :");
		    String bankName = inputObject.nextLine();
	            System.out.println("Enter Bank Account Number :");
		    long accountNumber = inputObject.nextLong();
                    Employee employee = employeeService.addData(employeeName, dateOfBirth, mobileNumber
                                  , departmentId, qualification, experience, employeeEmail, bankName, accountNumber);
		    System.out.println(employee.getEmployeeName() + "Employee Added Successfully..");
	        }
                catch (Exception e) {
	            System.out.println("An error occurred while creating an employee record: " 
				  + e.getMessage());
                }
            }
	}
	catch (EmployeeException e) {
	    System.out.println("An Error occured while creating employee Record.." + e.getMessage());
	}
    }

    /**
    * This method return the Employee Name if the Name valid
    * 
    * @return employeeName - Name of the Employee
    */
    public String getValidEmployeeName() {
	System.out.println("Enter Employee Name :");
        String employeeName = inputObject.nextLine();
	if (!Validator.isValidName(employeeName)) {
	    System.out.println("Enter Valid Name..");
	    getValidEmployeeName();
	}
	return employeeName;
    }

    /**
    * This method return the Employee Valid Date Of Birth
    * 
    * @return dateOfBirth - Date of Birth of the Employee
    */
    public LocalDate getValidDateOfBirth() {
        System.out.println("Enter Employee Date of Birth (yyyy-mm-dd) :");
	String dateOfBirthStr = inputObject.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
	return dateOfBirth;
    }

    /**
    * This method return the Employee Mobile Number if the Mobile Number valid
    * 
    * @return mobileNumber - Mobile number of the Employee 
    */
    public long getValidMobileNumber() {
	System.out.println("Enter Employee Mobile Number :");
        long mobileNumber = inputObject.nextLong();
	if (!Validator.validateMobile(mobileNumber)) {
	    System.out.println("Enter Valid Mobile Number..");
	    getValidMobileNumber();
	}
        return mobileNumber;
    }

    /**
    * This method return the Employee Email Id if the Email valid
    * 
    * @return employeeEmail - Email id of the Employee
    */
    public String getValidEmailId() {
	System.out.println("Enter Employee Email Id :");
        String employeeEmail = inputObject.nextLine();  
        if (!Validator.emailValidator(employeeEmail)) {
	    System.out.println("Enter Valid Email Id..");
	    getValidEmailId();
	}
	return employeeEmail;
    }

    /** 
    * This method to Display 
    * all the Records from the List
    * Retrieving from the Database
    */
    public void displayRecords() {
	try {
            if (employeeService.getEmployeeDetails().size() == 0) {
                System.out.println("No Data Found..");
                System.out.println("----------------------");
            }
	    String format = "| %-4s | %-15s | %-10s | %-5s | %-15s | %-15s" 
                                            + "| %-7s | %-5s | %-10s | %-10s\n";
            System.out.format(format, "Id", "Name","Department", "Age"
		             , "Mobile Number", "Email Id"
                             , "Qualification", "Experience", "Mentor", "Bank Name");
            System.out.println("-----------------------------------------"
				      + "----------------------------------------"
				      + "----------------------------------------");
	    for (Employee employee : employeeService.getEmployeeDetails()) {
		System.out.format(format, employee.getEmployeeId()
				 , employee.getEmployeeName()
				 , employee.getDepartment().getDepartmentName()
				 , employee.getAge()
				 , employee.getMobileNumber()
				 , employee.getEmployeeEmail()
				 , employee.getQualification()
				 , "\t\t" +employee.getExperience()
				 , employee.getNames()
				 , employee.getAccount().getBankName());
                System.out.println("---------------------------------------"
			         + "--------------------------------------"
			       	 + "--------------------------------------");
            }   
        } catch (Exception e) {
            System.out.println("An error occurred while displaying all records: " 
				+ e.getMessage());
        }
    }

    /**
    * This Method is used to display the 
    * Specific Employee Record in the list
    * retrieving from the Database
    */
    public void displayEmployeeRecord() {
	int updateId = 0;
    	try {
            System.out.println("Enter Employee Id to Display :");
            updateId = inputObject.nextInt();
	    Employee employee = employeeService.getEmployeeById(updateId);
	    if (employee != null) {
	        String format = "| %-4s | %-15s | %-10s | %-5s | %-15s | %-15s" 
                                + "| %-7s | %-5s | %-15s \n";
	        System.out.format(format, "Id", "Name", "Department", "Age"
		                , "Mobile Number", "Email Id"
                                , "Qualification", "Experience", "Mentor");
                System.out.println("---------------------------------------"
		    	        + "--------------------------------------"
		                + "--------------------------------------");
	        System.out.format(format, employee.getEmployeeId()
		    	        , employee.getEmployeeName()
                                , employee.getDepartment().getDepartmentName()
			        , employee.getAge()
			        , employee.getMobileNumber()
			        , employee.getEmployeeEmail()
			        , employee.getQualification()
			        , employee.getExperience()
                                , employee.getNames());
                System.out.println("---------------------------------------"
		  	        + "--------------------------------------"
		                + "--------------------------------------");
	    } else {
		System.out.println("Employee Data not Found..");
	    }
        }
        catch (EmployeeException e) {
	    System.out.println("An error Occurred while Display Employee Record for the Employee Id :"
				+updateId + e.getMessage());
        }
    }

    /**
    * This Method to Update an Employee Record
    * in the List with the Employee Id
    */
    public void updateEmployeeRecord() {
        String newName = "";
	long newMobileNumber = 0;
	String updatedEmail = "";
	String qualification = "";
	LocalDate updatedDateOfBirth = LocalDate.now();
	int experience = 0;
	try {
            System.out.println("Enter Employee Id to update :");
            int updateId = inputObject.nextInt();
            Employee employee = employeeService.getEmployeeById(updateId);
            System.out.println("What would you like to Update..");
            System.out.println("1.Name..");
            System.out.println("2.Mobile Number..");
            System.out.println("3.Email Id..");
            System.out.println("4.Qualification..");
            System.out.println("5.Experience..");
            System.out.println("6.Date of Birth..");
	    System.out.println("7.Department..");
            int updateChoice = inputObject.nextInt();
            inputObject.nextLine();
            switch(updateChoice) {
                case 1:
                    System.out.println("Enter New Name :");
                    newName = inputObject.nextLine();
		    employee.setEmployeeName(newName);
                    break;
                case 2:
                    System.out.println("Enter New Mobile Number :");
                    newMobileNumber = inputObject.nextLong();
                    employee.setMobileNumber(newMobileNumber);
                    break;
                case 3:
                    System.out.println("Enter New Email Id :");
                    updatedEmail = inputObject.nextLine();
                    employee.setEmployeeEmail(updatedEmail);
                    break;
                case 4:
                    System.out.println("Enter Correct Qualification :");
                    qualification = inputObject.nextLine();
                    employee.setQualification(qualification);
                    break;	
                case 5:
                    System.out.println("Enter Correct Experience :");
                    experience = inputObject.nextInt();
		    employee.setExperience(experience);
                    break;
                case 6:
                    System.out.println("Enter Correct Date of Birth :");
                    String updateDateOfBirth = inputObject.nextLine();
                    updatedDateOfBirth = LocalDate.parse(updateDateOfBirth);
		    employee.setDateOfBirth(updatedDateOfBirth);
                    break;	
		case 7:
		    employeeService.getDepartments();
 		    System.out.println("Enter Department Id to Update :");
	            int departmentId = inputObject.nextInt();
		    Department department = employeeService.getDepartmentObj(departmentId);
		    employee.setDepartment(department);
		    break;
                default:
                    System.out.println("Invalid Choice Enter correct Choice..");
                }	
                employeeService.updateRecord(employee);
        } catch (EmployeeException e) {
            System.out.println("An error occurred while updating the employee record: " 
				+ e.getMessage());
        }
    }

    /**
    * This method to Delete an Employee Record in the Database
    * with the Employee Id
    */
    public void deleteEmployeeRecord() {
	int deleteId = 0;
        try {
            System.out.println("Enter Employee Id to Delete records :");
            deleteId = inputObject.nextInt();
            employeeService.deleteRecord(deleteId);
        } catch (EmployeeException e) {
            System.out.println("An error occurred while deleting the employee record: " + deleteId 
				+ e.getMessage());
        }
    }

    /**
    * This method is add Mentor to the mentor table in the 
    * Database
    */
    public void addMentor() {
        int mentorId = 0;
	Mentor mentor = null;
        try {
            if (mentorService.getMentors().size() == 0) {
                System.out.println("No Mentors found !");
                System.out.println("Add an Mentor first...");
            } else { 
                System.out.println("Enter Employee Id : ");
                int employeeId = inputObject.nextInt();
                Employee employee = employeeService.getEmployeeById(employeeId);		
                if (employee != null) { 
                    System.out.println("Available Mentors : ");
                    mentorController.displayMentors();
                    System.out.println("Enter the Mentor Id : ");
                    mentorId = inputObject.nextInt();
                    mentor = mentorService.getMentor(mentorId);
                } else {
                    System.out.println("Employee not found..");
                }
                if (mentorService.getMentors().containsKey(mentorId)) {
                    mentorService.addEmployee(mentor, employee);
                } else {
                    System.out.println("Enter valid input..");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding a mentor: " + e.getMessage());
        }
    }

    /** 
    * This method is assign the Employee record to the
    * Mentor in the List
    */
    public void assignEmployeeToMentor() {
	int employeeId = 0;
	try {
            System.out.println("Enter Employee Id :");
            employeeId = inputObject.nextInt();
            mentorController.displayMentors();
            System.out.println("Enter Mentor Id :");
            int mentorId = inputObject.nextInt();
            Employee employee = employeeService.getEmployeeById(employeeId);
                if (employee.getEmployeeId() == employeeId) {
                    Mentor mentor = mentorService.getMentor(mentorId);
		    if (null == mentor) {
			System.out.println("Mentor not Found..");
		    } else {
			mentorService.addEmployee(mentor, employee);
		    	System.out.println("Mentor Assigned Successfully..");
		    }
                } else {
		    System.out.println("Employee Data not found..");
		}
        } catch (EmployeeException e) {
            System.out.println("An error occurred while assigning an employee: Id : " 
		+ employeeId + e.getMessage());
        }
    }
}
