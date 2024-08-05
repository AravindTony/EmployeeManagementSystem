package com.mentor.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mentor.dao.MentorDao;
import com.model.Mentor;
import com.model.Employee;
import com.customexception.EmployeeException;
import com.mentor.service.MentorService;
import com.mentor.dao.MentorDaoImpl;

/** 
 * <p>
 * This class is the Service Implementation for the Mentor 
 * To insert the data about the Mentor to the Dao
 * </p>
 * @author Aravind
 */
public class MentorServiceImpl implements MentorService {
    MentorDao mentorDao = new MentorDaoImpl();

    @Override
    public Mentor getMentor(int id) throws EmployeeException {
        return mentorDao.getMentor(id);        
    }

    @Override
    public void addMentor(String name) throws EmployeeException {
        mentorDao.addMentor(name);
    }

    @Override
    public void addEmployee(Mentor mentor, Employee employee) throws EmployeeException {
        mentorDao.addEmployee(mentor, employee);
    }

    @Override
    public Map<Integer, Mentor> getMentors() throws EmployeeException {
        return mentorDao.getMentors();
    } 
    
    @Override
    public Set<Employee> getEmployeesByMentor(int mentorId) throws EmployeeException {
	return mentorDao.getEmployeesByMentor(mentorId);
    }

    @Override 
    public void deleteMentor(int mentorId) throws EmployeeException {
	mentorDao.deleteMentor(mentorId);
    }
}