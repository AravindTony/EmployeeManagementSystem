package com.mentor.service;

import java.util.Map;
import java.util.List;
import java.util.Set;

import com.customexception.EmployeeException;
import com.model.Mentor;
import com.model.Employee;

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
     * @param id - Id of the Mentor
     * 
     * @return Mentor - Mentor with Mentor Id
     * 
     * @throws EmployeeException while get Mentor with Mentor Id
     */
    public Mentor getMentor(int id) throws EmployeeException;

    /**
     * <p>
     * This method add Mentor to the Database
     * </p>
     * @param name - Name of the Mentor
     *
     * @throws EmployeeException while adding Mentor 
     */
    public void addMentor(String name) throws EmployeeException;
    
    /**
     * <p>
     * This method add Employee to the Mentor with the 
     * Mentor Id and Employee
     * </p>
     * @param id - Id of the Mentor
     * @param Employee - Employee Object
     * 
     * @return Mentor - Mentor with Mentor Id
     * 
     * @throws EmployeeException while Add Employee to the Mentor
     */
    public void addEmployee(Mentor mentor, Employee employee) throws EmployeeException;

    /**
     * <p>
     * This method return the Mentor Object as Map
     * </p>
     * @return Mentor Objects
     * 
     * @throws EmployeeException while getting Mentors 
     */
    public Map<Integer, Mentor> getMentors() throws EmployeeException;

    /**
     * <p>
     * This method return the Employees by the Mentor Id
     * </p>
     * @param mentorId - Id of the Mentor
     *
     * @return Employees List
     * 
     * @throws EmployeeException while get Employees by Mentor 
     */
    public Set<Employee> getEmployeesByMentor(int mentorId) throws EmployeeException;

    /**
     * <p>
     * This method to Delete Mentor with Mentor Id
     * </p>
     * @param mentorId - Id of the Mentor to Delete
     * 
     * @throws EmployeeException while Delete Mentor
     */
    public void deleteMentor(int mentorId) throws EmployeeException;
} 