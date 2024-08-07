package com.ideas2it.employee.controller;

import java.util.Map;
import java.time.LocalDate;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.EmployeeServiceImpl;
import com.ideas2it.model.Mentor;
import com.ideas2it.model.Employee;
import com.ideas2it.mentor.service.MentorService;
import com.ideas2it.model.Department;
import com.ideas2it.customexception.EmployeeException;
import com.ideas2it.mentor.service.MentorServiceImpl;
import com.ideas2it.util.Validator;

/**
 * <p>
 *This class is the Controller for Employee get all the Information's
 * about the Employee and also various methods like
 * Create Employee Record 
 * Display the Employee record(s)
 * Update the Employee Record
 * Delete the Employee Record in the Repository
 *</p>
 * @author Aravind
 */
public class EmployeeController {
    private static final Scanner inputObject = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger();
    private final MentorService mentorService = new MentorServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * <p>
     * This method used to get the credentials 
     * by the User to create EmployeeRecord.
     * </p>
     */
    public void createEmployeeRecord() {
        try {
            if (employeeService.getEmployeeDepartments().isEmpty()) {
                    logger.warn("Add a department to continue.. Departments are Empty..");
            }
            else {
                try {
                    System.out.println("Employee Management System..");
                    logger.debug("Employee Credentials Validation Started..");
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
                    logger.debug("Employee Credentials Validation Completed..");
                    Employee employee = employeeService.addData(employeeName, dateOfBirth, mobileNumber
                                      , departmentId, qualification, experience, employeeEmail, bankName, accountNumber);
                    logger.info("{} Employee Added Successfully..", employee.getEmployeeName());
                } catch (Exception e) {
                    logger.error("An error occurred while creating an employee record: {}", e.getMessage());
                }
            }
        } catch (EmployeeException e) {
            logger.error("An Error occurred while creating employee Record..{}", e.getMessage());
	    }
    }

    /**
     * <p>
     * This method return the Employee Name if the Name valid
     * </p>
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
     * <p>
     * This method return the Employee Valid Date Of Birth
     * </p>
     * @return dateOfBirth - Date of Birth of the Employee
     */
    public LocalDate getValidDateOfBirth() {
        System.out.println("Enter Employee Date of Birth (yyyy-mm-dd) :");
	    String dateOfBirthStr = inputObject.nextLine();
        return LocalDate.parse(dateOfBirthStr);
    }

    /**
     * </p>
     * This method return the Employee Mobile Number if the Mobile Number valid
     * </p>
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
     * <p>
     * This method return the Employee Email Id if the Email valid
     * </p>
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
     * <p>
     * This method to Display 
     * all the Records from the List
     * Retrieving from the Database
     * </p>
     */
    public void displayRecords() {
        logger.debug("Retrieving the List of Employees..");
	try {
            if (employeeService.getEmployeeDetails().isEmpty()) {
                logger.info("No Data Found..");
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
        logger.error("An error occurred while displaying all records: {}", e.getMessage());
        }
    }

    /**
     * <p>
     * This Method is used to display the 
     * Specific Employee Record in the list
     * retrieving from the Database
     * </p>
     */
    public void displayEmployeeRecord() {
	    int displayId = 0;
    	try {
            System.out.println("Enter Employee Id to Display :");
            displayId = inputObject.nextInt();
            logger.debug("Searching Employee with this Id :{}", displayId);
	        Employee employee = employeeService.getEmployeeById(displayId);
	        if (null != employee) {
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
                logger.info("Employee Data not Found with this Id :{}", displayId);
	        }
        }
        catch (EmployeeException e) {
            logger.error("An error Occurred while Display Employee Record for the Employee Id :{}{}", displayId, e.getMessage());
        }
    }

    /**
     * <p>
     * This Method to Update an Employee Record
     * in the List with the Employee Id
     * </p>
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
            logger.debug("Employee Updation started with Choice :{}", updateChoice);
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
            logger.error("An error occurred while updating the employee record: {}", e.getMessage());
        }
    }

    /**
     * <p>
     * This method to Delete an Employee Record in the Database
     * with the Employee Id 
     * </p>
     */
    public void deleteEmployeeRecord() {
	int deleteId = 0;
        try {
            System.out.println("Enter Employee Id to Delete records :");
            deleteId = inputObject.nextInt();
            employeeService.deleteRecord(deleteId);
            logger.info("Employee with This id Deleted Successfully : {}", deleteId);
        } catch (EmployeeException e) {
            logger.error("An error occurred while deleting the employee record: {}{}", deleteId, e.getMessage());
        }
    }

    /** 
     *<p>
     * This method is assign the Employee record to the
     * Mentor in the List
     * </p>
     */
    public void assignEmployeeToMentor() {
        int employeeId = 0;
        try {
            System.out.println("Enter Employee Id :");
            employeeId = inputObject.nextInt();
            for (Map.Entry<Integer, Mentor> e : mentorService.getMentors().entrySet()) {
                System.out.println(e.getKey() + "." + e.getValue().getMentorName());
            }
            System.out.println("Enter Mentor Id :");
            int mentorId = inputObject.nextInt();
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee.getEmployeeId() == employeeId) {
                Mentor mentor = mentorService.getMentor(mentorId);
		        if (null == mentor) {
                    logger.info("Mentor not Found with this Id : {}", mentorId);
		        } else {
			        mentorService.addEmployee(mentor, employee);
                    logger.info("{} Mentor Assigned Successfully..", mentor.getMentorName());
                }
            } else {
                logger.info("Employee Data not found with this Id : {}", employeeId);
            }
        } catch (EmployeeException e) {
            logger.error("An error occurred while assigning an employee: Id : {}{}", employeeId, e.getMessage());
        }
    }
}
