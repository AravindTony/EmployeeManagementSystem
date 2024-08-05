package com.department.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.Employee;
import com.customexception.EmployeeException;
import com.connectionmanager.HibernateManager;
import com.model.Department;
import com.department.dao.DepartmentWarehouseImpl;

/**
* <p>This Class is a Dao for Department handles the Operations
* like Insertion, Updation, Retrieving, Deletion for the Department Information
* From the Database. </p>
* @author Aravind
*/
public class DepartmentWarehouseImpl implements DepartmentWarehouse {
 
    @Override
    public void insertDepartment(String departmentName) throws EmployeeException {
       Session session = HibernateManager.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Department department = new Department(departmentName);
            Integer id = (Integer) session.save(department);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while adding department of name : " + departmentName, e);
        } finally {
            session.close();
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
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while fetching available departments : ", e);
        } finally {
            session.close();
        }
        return departments;
    }

    @Override
    public Department getDepartmentObject(int departmentId) throws EmployeeException {
        Session session = HibernateManager.getFactory().openSession();
        Transaction transaction = null;
        Department department = null;
        try {
            transaction = session.beginTransaction();
            department = session.get(Department.class, departmentId);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while fetching department of id : " + departmentId, e);
        } finally {
            session.close();
        } 
        return department;
    }

    public Set<Employee> getEmployeesByDepartment(int departmentId) throws EmployeeException {
	Set<Employee> employees = new HashSet<>();
        Session session = HibernateManager.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, departmentId);
            if (department != null) {
                Hibernate.initialize(department.getEmployees());
                employees = department.getEmployees();
            }
            transaction.commit();            
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while fetching employees of Department Id : " + departmentId, e);
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public void deleteDepartment(int departmentId) throws EmployeeException {
	Session session = HibernateManager.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("DELETE FROM Department WHERE departmentId = :departmentId");
            query.setParameter("departmentId", departmentId);
            query.executeUpdate();          
            transaction.commit();
        } catch (HibernateException e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while deleting department of id : " + departmentId, e);
        } finally {
            session.close();
        }
    }	

    @Override
    public void updateDepartmentName(Department department) throws EmployeeException {
	Session session = HibernateManager.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while updating department of ID : " + department.getDepartmentId(), e);
        } finally {
            session.close();
        }
    }
}