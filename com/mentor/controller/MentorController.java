package com.mentor.controller;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import com.mentor.service.MentorService;
import com.employee.service.EmployeeService;
import com.model.Mentor;
import com.model.Employee;
import com.customexception.EmployeeException;
import com.mentor.service.MentorServiceImpl;

/** 
* This class used to get inputs and display
* the information about the Mentor
* like Mentor name and Id
* @author Aravind
*/
public class MentorController {
    MentorService mentorService = new MentorServiceImpl();
    Scanner inputObject = new Scanner(System.in);

    /** This Method is to get the Choice of Mentor Services
    * 1. Add the Mentor to the Database
    * 2. Display the Employees by the Mentor 
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
		        System.out.println("Mentor Added Successfully..");
		        break;
		    }
	            catch (EmployeeException e) {
		 	System.out.println("Unable to Add Mentor to Mentor Repository:" 
			                  + name + e.getMessage());
		    }

	        case 2:
                    if (mentorService.getMentors().size() == 0) {
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
                            if (employees.size() != 0) {
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
                        }
			catch (EmployeeException e) {
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
            }
        }
	catch (Exception e) {
	    System.out.println("An error occured while entering this Choice :" + userChoice + e.getMessage());
        }
    }
    
    /** 
    * This method display the mentors in the HashMap Object
    */
    public void displayMentors() {	
        try {
	    for (Map.Entry<Integer, Mentor> e : mentorService.getMentors().entrySet()) {
                System.out.println(e.getKey() + "." + e.getValue().getMentorName());
            }
        }
	catch (EmployeeException e) {
	    System.out.println("An error occured while Display Mentors.." + e.getMessage());
	}
    }
}