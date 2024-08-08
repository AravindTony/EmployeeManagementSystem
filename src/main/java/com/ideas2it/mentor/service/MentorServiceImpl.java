package com.ideas2it.mentor.service;

import java.util.Map;
import java.util.Set;

import com.ideas2it.mentor.dao.MentorDao;
import com.ideas2it.model.Mentor;
import com.ideas2it.model.Employee;
import com.ideas2it.customException.EmployeeException;
import com.ideas2it.mentor.dao.MentorDaoImpl;

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
    public void deleteMentor(int mentorId) throws EmployeeException {
	    mentorDao.deleteMentor(mentorId);
    }
}