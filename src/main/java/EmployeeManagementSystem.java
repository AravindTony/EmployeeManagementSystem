import java.util.Scanner;

import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.department.controller.DepartmentController;
import com.ideas2it.mentor.controller.MentorController;

/** 
 * <p>
 * EmployeeManagementSystem class simulates a Management System 
 * for Employees 
 * </p>
 * @author Aravind
 */

public class EmployeeManagementSystem {
    private static boolean isExit = true;
    private static final EmployeeController employeeController = new EmployeeController();
    private static final MentorController mentorController = new MentorController();
    private static final DepartmentController departmentController = new DepartmentController();

    /** 
     * <p>
     * This method get the choice from the user 
     * to perform Employee Operation.
     * </p>
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
                    isExit = false;
		    System.out.println("Exiting..");
		    break;

	        default:
		    System.out.println("Enter Valid Number :");
                    getChoice();
	    }
        } while (isExit);
    } 

    public static void main(String[] args) {
	getChoice();
    }
}