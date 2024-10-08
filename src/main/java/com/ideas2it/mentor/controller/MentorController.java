package com.ideas2it.mentor.controller;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.mentor.service.MentorService;
import com.ideas2it.model.Mentor;
import com.ideas2it.model.Employee;
import com.ideas2it.customException.EmployeeException;
import com.ideas2it.mentor.service.MentorServiceImpl;

/** 
 * <p>
 * This class used to get inputs and display
 * the information about the Mentor
 * like Mentor name and Id 
 * </p>
 * @author Aravind
 */
public class MentorController {
    private final MentorService mentorService = new MentorServiceImpl();
    private static final Logger logger = LogManager.getLogger();
    private final Scanner inputObject = new Scanner(System.in);

    /** 
     * <p>
     * This Method is to get the Choice of Mentor Services
     * 1. Add the Mentor to the Database
     * 2. Display the Employees by the Mentor 
     * </p>
     */
    public void getDetails() {
		int userChoice = 0;
        try {
   	    	System.out.println("1.Add a Mentor..");
            System.out.println("2.Display Employees by Mentor Wise..");
	    	System.out.println("3.Delete Mentor..");
            userChoice = inputObject.nextInt();
	    	switch (userChoice) {
	        	case 1:
		    		inputObject.nextLine();
					System.out.println("Enter Mentor Name :");
		    		String name = inputObject.nextLine();
		    		try {
						mentorService.addMentor(name);
						logger.info("{}Mentor added Successfully..", name);
						break;
		    		} catch (EmployeeException e) {
		 				System.out.println("Unable to Add Mentor to Mentor Repository:"
			                  			  + name + e.getMessage());
		    		}
	        	case 2:
                    if (mentorService.getMentors().isEmpty()) {
                        System.out.println("No Mentors found");
                    } 
		    		else {
						int mentorId = 0;
                        try {
			    			System.out.println("Available Mentors : ");
                            displayMentors();
                            System.out.println("Enter Mentor Id :");
                            mentorId = inputObject.nextInt();
                            Mentor mentor = mentorService
                                              .getMentor(mentorId);
			    			List<Employee> employees = new ArrayList<>(mentor.getEmployees());
                            String format = ("%-5s | %-10s | %-15s | %-5s | %-10s | %-15s |"
                                         + " %-7s | %-5s");
                            System.out.format(format, "ID", "Name", "Department", "Age", "Mobile.No", 
                                         "Email Id", "Qualification", "Experience");
                            System.out.println();
                            if (!employees.isEmpty()) {
                                for (Employee employee : employees) {
									System.out.format(format, employee.getEmployeeId()
									 				, employee.getEmployeeName()
								 					, employee.getDepartment().getDepartmentName()
													, employee.getAge()
													, employee.getMobileNumber()
													, employee.getEmployeeEmail()
													, employee.getQualification()
													, "\t" +employee.getExperience());
		                    		System.out.println();
                                }
                            } 
		            		else {
                                System.out.println("No employees found !");
                            }
                        } catch (EmployeeException e) {
			    			System.out.println("Error while finding this Mentor : " + mentorId + e.getMessage());
						}
                   }
                   break;
	       		case 3:
	           		inputObject.nextLine();
				   	int deleteMentorId = 0;
				   	try {
					   	System.out.println("Enter Mentor Id to Delete :");
					   	deleteMentorId = inputObject.nextInt();
					   	mentorService.deleteMentor(deleteMentorId);
				   	} catch (EmployeeException e) {
					   	System.out.println("Error while Delete Mentor with this Id :" + deleteMentorId + e.getMessage());
				   	}
				default:
					System.out.println("Invalid Option..");
            }
        } catch (Exception e) {
	    	System.out.println("An error occurred while entering this Choice :" + userChoice + e.getMessage());
        }
    }
    
    /** 
     * <p>
     * This method display the mentors in the HashMap Object
     * </p>
     */
    public void displayMentors() {	
        try {
	    	for (Map.Entry<Integer, Mentor> e : mentorService.getMentors().entrySet()) {
                System.out.println(e.getKey() + "." + e.getValue().getMentorName());
            }
        } catch (EmployeeException e) {
	    	System.out.println("An error occurred while Display Mentors.." + e.getMessage());
		}
    }
}