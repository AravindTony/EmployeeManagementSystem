package com.ideas2it.mentor.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ideas2it.model.Mentor;
import com.ideas2it.model.Employee;
import com.ideas2it.customException.EmployeeException;
import com.ideas2it.connectionManager.HibernateManager;

/** 
 * <p>
 * This class Handles the Operations like Insertion 
 * Updation, Retrieving and Deletion of Mentors Information
 * from the Database
 *</p>
 * @author Aravind
 */
public class MentorDaoImpl implements MentorDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Mentor getMentor(int mentorId) throws EmployeeException {
        Session session = HibernateManager.getFactory().openSession();
        Transaction transaction = null;
        Mentor mentor = null;
        try {
            transaction = session.beginTransaction();
            Query<Mentor> query = session.createQuery("from Mentor where mentorId = :mentorId and isDeleted = false", Mentor.class);
            query.setParameter("mentorId", mentorId);
            mentor = query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if(null != transaction) {
                transaction.rollback();
            }
	        logger.error("An Error occurred while get Mentor..");
            throw new EmployeeException("Error while get Mentor with id : " + mentorId, e);
        } finally {
            if (null != session) {
                session.close();
	        }
        }
        return mentor;
    }

    @Override
    public Map<Integer, Mentor> getMentors() throws EmployeeException {
        Session session = HibernateManager.getFactory().openSession();
        Map<Integer, Mentor> mentors = new HashMap<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<Mentor> query = session.createQuery("from Mentor where isDeleted = false", Mentor.class);
            List<Mentor> mentorsFromDataBase = query.list();
            for (Mentor mentor : mentorsFromDataBase) {
                mentors.put(mentor.getMentorId(), mentor);
            }
            transaction.commit();     
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("An error occurred while get Mentors..");
            throw new EmployeeException("Error while fetching available Mentors !", e);
        } finally {
            if (null != session) {
                session.close();
	        }
        }
        return mentors;
    }    

    @Override
    public void addMentor(String name) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Mentor mentor = new Mentor(name);
            Integer id = (Integer) session.save(mentor);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("An Error occurred while add Mentor..");
            throw new EmployeeException("Error while adding Mentor of name : " + name, e);
        }
    }

    @Override
    public void addEmployee(Mentor mentor, Employee employee) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employeeObject = session.get(Employee.class, employee.getEmployeeId());
            Mentor mentorObject = session.get(Mentor.class, mentor.getMentorId());
            Set<Mentor> mentors = employeeObject.getMentors();
            Set<Employee> employees = mentorObject.getEmployees();
            mentors.add(mentorObject);
            employees.add(employeeObject);
            session.saveOrUpdate(employeeObject);
            session.saveOrUpdate(mentorObject);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
        } catch (Exception e) {
            logger.error("Error while Adding Employee to Mentor..{}", e.getMessage());
            throw new EmployeeException("Error while adding mentor " + mentor.getMentorName()
                    + "to employee id : " + employee.getEmployeeName(), e);
        }
    }
    
    @Override
    public Set<Employee> getEmployeesByMentor(int mentorId) throws EmployeeException {
        Transaction transaction = null;
        Set<Employee> employees = new HashSet<>();
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "select m from Mentor m left join fetch m.employees where m.id = :mentorId";
            Mentor mentor = session.createQuery(hql, Mentor.class)
                    .setParameter("mentorId", mentorId)
                    .uniqueResult();
            if (null != mentor) {
                Hibernate.initialize(mentor.getEmployees());
                employees = mentor.getEmployees();
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("An Error occurred while get Employees By mentor..");
            throw new EmployeeException("Error while getting employees of mentor id : " + mentorId, e);
        }
        return employees;
    }

    @Override
    public void deleteMentor(int mentorId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            String deleteQuery = "update Mentor set isDeleted = :isDeleted WHERE id = :mentorId";
            Query<?> query = session.createQuery(deleteQuery);
            query.setParameter("isDeleted", true);
            query.setParameter("mentorId", mentorId);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("An error occurred while delete Mentor..");
            throw new EmployeeException("Error while deleting employee of id : " + mentorId, e);
        }
    }
}