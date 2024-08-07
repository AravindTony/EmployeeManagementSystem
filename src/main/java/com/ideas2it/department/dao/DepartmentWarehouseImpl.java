package com.ideas2it.department.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.model.Employee;
import com.ideas2it.customexception.EmployeeException;
import com.ideas2it.connectionmanager.HibernateManager;
import com.ideas2it.model.Department;

/**
 * <p>
 * This Class is a Dao for Department handles the Operations
 * like Insertion, Updation, Retrieving, Deletion for the Department Information
 * From the Database. 
 * </p>
 * @author Aravind
 */
public class DepartmentWarehouseImpl implements DepartmentWarehouse {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void insertDepartment(String departmentName) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Department department = new Department(departmentName);
            Integer id = (Integer) session.save(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("Error Occurred while adding department to Database : {}", departmentName);
            throw new EmployeeException("Error while adding department of name : " + departmentName, e);
        }
    }

    @Override
    public Map<Integer, Department> getEmployeeDepartments() throws EmployeeException {
        Session session = HibernateManager.getFactory().openSession();
        Map<Integer, Department> departments = new HashMap<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery("FROM Department", Department.class);
            List<Department> departmentsFromDataBase = query.list();
            for (Department department : departmentsFromDataBase) {
                departments.put(department.getDepartmentId(), department);
            }
        } catch (HibernateException e) {
            if(null != transaction) {
                transaction.rollback();
            }
	    logger.error("An error occurred while Get Departments..");
            throw new EmployeeException("Error while fetching available departments : ", e);
        } finally {
            if (null != session) {
                session.close();
	    }
        }
        return departments;
    }

    @Override
    public Department getDepartmentObject(int departmentId) throws EmployeeException {
        Session session = HibernateManager.getFactory().openSession();
        Transaction transaction = null;
        Department department;
        try {
            transaction = session.beginTransaction();
            department = session.get(Department.class, departmentId);
            transaction.commit();
        } catch (HibernateException e) {
            if(null != transaction) {
                transaction.rollback();
            }
	    logger.error("Error Occurred While get Department Object..");
            throw new EmployeeException("Error while fetching department of id : " + departmentId, e);
        } finally {
            if (null != session) {
                session.close();
	    }
        } 
        return department;
    }

    public Set<Employee> getEmployeesByDepartment(int departmentId) throws EmployeeException {
        Transaction transaction = null;
        Set<Employee> employees = new HashSet<>();
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, departmentId);
            if (null != department) {
                Hibernate.initialize(department.getEmployees());
                employees = department.getEmployees();
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("Error Occurred while get Employees by Department..");
            throw new EmployeeException("Error while fetching employees of Department Id : " + departmentId, e);
        }
        return employees;
    }

    @Override
    public void deleteDepartment(int departmentId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("DELETE FROM Department WHERE departmentId = :departmentId");
            query.setParameter("departmentId", departmentId);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("An error while Delete Department..");
            throw new EmployeeException("Error while deleting department of id : " + departmentId, e);
        }
    }	

    @Override
    public void updateDepartmentName(Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("Error while update Department Name..");
            throw new EmployeeException("Error while updating department of ID : " + department.getDepartmentId(), e);
        }
    }
}