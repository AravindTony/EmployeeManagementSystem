package com.ideas2it.mentor.dao;

import java.util.Map;
import java.util.Set;

import com.ideas2it.model.Mentor;
import com.ideas2it.model.Employee;
import com.ideas2it.customexception.EmployeeException;

/** 
 * <p>
 * This Interface has Abstract Methods Handles the Operations like Insertion 
 * Updation, Retrieving and Deletion of Mentors Information
 * from the Database
 * </p>
 * @author Aravind
 */
public interface MentorDao {
    /** 
     * <p>
     * This method return the Mentor with the Mentor id
     * </p>
     * @param id - ID of the Mentor
     *
     * @return mentor - Mentor as the Object
     *
     * @throws EmployeeException while return the Mentor
     */
    Mentor getMentor(int id) throws EmployeeException;

    /**
     * <p>
     * This method return the all the Mentors
     * </p>
     * @return mentors - Mentors as the Map Object
     * 
     * @throws EmployeeException while getting Mentors
     */
    Map<Integer, Mentor> getMentors() throws EmployeeException;

    /**
     * <p>
     * This method add the Mentor to the Database
     * </p>
     * @param name - Name of the Mentor
     *
     * @throws EmployeeException while add the Mentor to the Dao
     */
    void addMentor(String name) throws EmployeeException;

    /**
     * <p>
     * This method add the Employee to the Mentor with mentor id
     * </p>
     * @param mentor - Mentor as Object
     * @param employee - Employee as Object
     *
     *
     * @throws EmployeeException while return the Mentor
     */
    void addEmployee(Mentor mentor, Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method return the Employees with the Mentor id
     * </p>
     * @param mentorId - ID of the Mentor
     *
     * @return Employees - Employees as List
     *
     * @throws EmployeeException while return the Employees by Mentor
     */
    Set<Employee> getEmployeesByMentor(int mentorId) throws EmployeeException;

    /**
     * <p>
     * This method delete Mentor in the Database with Mentor id
     * </p>
     * @param mentorId - ID of the Mentor
     *  
     * @throws EmployeeException while Delete the Mentor
     */
    void deleteMentor(int mentorId) throws EmployeeException;
}