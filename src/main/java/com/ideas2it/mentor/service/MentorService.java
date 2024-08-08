package com.ideas2it.mentor.service;

import java.util.Map;
import java.util.Set;

import com.ideas2it.customException.EmployeeException;
import com.ideas2it.model.Mentor;
import com.ideas2it.model.Employee;

/**
 * <p>
 * This interface has Abstract Methods to Implement Service Methods for 
 * Mentor 
 * </p>
 * @author Aravind
 */
public interface MentorService {
    /**
     * <p>
     * This method return the Particular Mentor with the 
     * Mentor Id
     * </p>
     * @param id - ID of the Mentor
     * 
     * @return Mentor - Mentor with Mentor ID
     * 
     * @throws EmployeeException while get Mentor with Mentor ID
     */
    Mentor getMentor(int id) throws EmployeeException;

    /**
     * <p>
     * This method add Mentor to the Database
     * </p>
     * @param name - Name of the Mentor
     *
     * @throws EmployeeException while adding Mentor 
     */
    void addMentor(String name) throws EmployeeException;
    
    /**
     * <p>
     * This method add Employee to the Mentor with the 
     * Mentor Id and Employee
     * </p>
     * @param mentor - Mentor as Object
     * @param employee - Employee as Object
     * 
     * @throws EmployeeException while Add Employee to the Mentor
     */
    void addEmployee(Mentor mentor, Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method return the Mentor Object as Map
     * </p>
     * @return Mentor Objects
     * 
     * @throws EmployeeException while getting Mentors 
     */
    Map<Integer, Mentor> getMentors() throws EmployeeException;

    /**
     * <p>
     * This method to Delete Mentor with Mentor Id
     * </p>
     * @param mentorId - ID of the Mentor to Delete
     * 
     * @throws EmployeeException while Delete Mentor
     */
    void deleteMentor(int mentorId) throws EmployeeException;
} 