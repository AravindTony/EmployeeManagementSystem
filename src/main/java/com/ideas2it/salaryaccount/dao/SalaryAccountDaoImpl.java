package com.ideas2it.salaryaccount.dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.connectionmanager.HibernateManager;

import com.ideas2it.model.SalaryAccount;

/**
* This class handles the Database Operation like insert 
* the Account details
*/
public class SalaryAccountDaoImpl implements SalaryAccountDao {

    @Override
    public void insertSalaryAccount(SalaryAccount account) {
	Session session = HibernateManager.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Integer id = (Integer) session.save(account);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error while adding Account Details : " + account.getBankName());
        } finally {
            session.close();
        }
    }
}