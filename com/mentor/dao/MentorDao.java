package com.mentor.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

import com.model.Mentor;
import com.model.Employee;
import com.customexception.EmployeeException;

/** 
 * <p>
 * This Interface has Abstract Methods Handles the Operations like Insertion 
 * Updation, Retrieving and Deletion of Mentors Information
 * from the Database
 * </p>
 * @author Aravind
 */
public interface MentorDao {
    static Map<Integer, Mentor> mentors= new HashMap<>();

    /** 
     * <p>
     * This method return the Mentor with the Mentor id
     * </p>
     * @param id - Id of the Mentor
     *
     * @return mentor - Mentor as the Object
     *
     * @throws Employee Exception while return the Mentor
     */
    public Mentor getMentor(int id) throws EmployeeException;

    /**
     * <p>
     * This method return the all the Mentors
     * </p>
     * @return mentors - Mentors as the Map Object
     * 
     * @throws EmployeeException while getting Mentors
     */
    public Map<Integer, Mentor> getMentors() throws EmployeeException;

    /**
     * <p>
     * This method add the Mentor to the Database
     * </p>
     * @param name - Name of the Mentor
     *
     * @throws Employee Exception while add the Mentor to the Dao
     */
    public void addMentor(String name) throws EmployeeException;

    /**
     * <p>
     * This method add the Employee to the Mentor with mentor id
     * </p>
     * @param id - Id of the Mentor
     * @param employee - Employee as the Object
     *
     *
     * @throws Employee Exception while return the Mentor
     */
    public void addEmployee(Mentor mentor, Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method return the Employees with the Mentor id
     * </p>
     * @param id - Id of the Mentor
     *
     * @return Employees - Employees as List
     *
     * @throws Employee Exception while return the Employees by Mentor
     */
    public Set<Employee> getEmployeesByMentor(int mentorId) throws EmployeeException;

    /**
     * <p>
     * This method delete Mentor in the Database with Mentor id
     * </p>
     * @param mentorId - Id of the Mentor
     *  
     * @throws EmployeeException while Delete the Mentor
     */
    public void deleteMentor(int mentorId) throws EmployeeException;
}