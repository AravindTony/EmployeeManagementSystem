package com.ideas2it.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.model.Employee;
import com.ideas2it.customException.EmployeeException;
import com.ideas2it.connectionManager.HibernateManager;

/** 
 * <p>
 * This class handles all the Database Operations like
 * Insertion, Display the Records, Update and Delete the Records
 * </p>
 * @author Aravind
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Employee> getRecords() throws EmployeeException {
        List<Employee> employeeRecords = new ArrayList<>();
        try (Session session = HibernateManager.getFactory().openSession()) {
            Query<Employee> query =  session.createQuery("from Employee where isDeleted = :isDeleted", Employee.class)
                                            .setParameter("isDeleted", false);
            employeeRecords = query.list();
        } catch (HibernateException e) {
            logger.error("Error while get Employee Records..");
            throw new EmployeeException("Error while get All employee records..", e);
        }
        return employeeRecords;
    }

    @Override
    public Employee insertData(Employee employee) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("Error occurred while Adding this Employee :{}", employee.getEmployeeName());
            throw new EmployeeException("Error occurred while adding employee of name : " + employee.getEmployeeName(), e);
        }
	    return employee;
    }

    @Override
    public void updateRecord(Employee employee) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("Error while updating with Employee :{}", employee.getEmployeeName());
            throw new EmployeeException("Error while updating employee of ID : " + employee.getEmployeeId(), e);
        }
    }
    
    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeException {
        Employee employee;
        try (Session session = HibernateManager.getFactory().openSession()) {
            employee = session.createQuery("from Employee where isDeleted = false and employeeId = :employeeId", Employee.class)
			                  .setParameter("employeeId", employeeId).uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error while get Employee by this Id: {}", employeeId);
            throw new EmployeeException("Error while getting employee of Id : " + employeeId, e);
        }
        return employee;
    }
 
    @Override
    public void deleteRecord(int deleteId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            String deleteQuery = "update Employee set isDeleted = :isDeleted where id = :deleteId";
            Query<?> query = session.createQuery(deleteQuery);
            query.setParameter("isDeleted", true);
            query.setParameter("deleteId", deleteId);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("Error while deleting employee with this Id :{}", deleteId);
            throw new EmployeeException("Error while deleting employee of Id : " + deleteId, e);
        }
    }
}