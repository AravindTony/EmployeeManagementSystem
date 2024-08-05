import java.util.Scanner;
import java.util.NoSuchElementException;

import com.employee.controller.EmployeeController;
import com.department.controller.DepartmentController;
import com.mentor.controller.MentorController;

/** 
* EmployeeManagementSystem class simulates a Management System 
* for Employees
* @author Aravind
*/

public class EmployeeManagementSystem {
    static boolean bool = true;
    static EmployeeController employeeController = new EmployeeController();
    static MentorController mentorController = new MentorController();
    static DepartmentController departmentController = new DepartmentController();

    /** 
    * This method get the choice from the user 
    * to perform Employee Operations
    *
    * @param choice choice get from the user
    */
    public static void getChoice() {
        do {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Employee Management System..");
	    System.out.println("1.Display Employees List..");
	    System.out.println("2.Create an Employee Record..");
	    System.out.println("3.Update an Employee Record..");
	    System.out.println("4.Display an Employee Record..");
	    System.out.println("5.Delete an Employee Record..");
	    System.out.println("6.Add Department..");
	    System.out.println("7.Update Department Name..");
	    System.out.println("8.Delete Department..");
	    System.out.println("9.Display Department Employees..");
            System.out.println("10.Mentor Services..");
            System.out.println("11.Assign Mentor to Employee..");
	    System.out.println("12.Exit..");
            System.out.println();
	    int choice = scanner.nextInt();
	    switch (choice) {
                case 1:
		    employeeController.displayRecords();
		    break;

	        case 2:
	            employeeController.createEmployeeRecord();
		    break;

	        case 3:
		    employeeController.updateEmployeeRecord();
		    break;

	        case 4: 
		    employeeController.displayEmployeeRecord();
		    break;

	        case 5:
                    employeeController.deleteEmployeeRecord();
		    break;
		
		case 6:
                    departmentController.addDepartment();
		    break;
	
		case 7:
		    departmentController.updateDepartment();
		    break;

		case 8:
		    departmentController.deleteDepartment();
                    break;      
		
		case 9:
		    departmentController.displayDepartmentEmployees();
		    break;		    
	
		case 10:
		    mentorController.getDetails();
		    break;

		case 11:
		    employeeController.assignEmployeeToMentor();
		    break;

	        case 12:
                    bool = false;
		    System.out.println("Exiting..");
		    break;

	        default:
		    System.out.println("Enter Valid Number :");
                    getChoice();
	    }
        } while (bool);
    } 

    public static void main(String[] args) {
	getChoice();
    }
}