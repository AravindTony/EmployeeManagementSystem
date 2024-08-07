package com.ideas2it.salaryaccount.dao;

import org.hibernate.HibernateException;
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
        Transaction transaction = null;
        try (Session session = HibernateManager.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Integer id = (Integer) session.save(account);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error while adding Account Details : " + account.getBankName());
        }
    }
}