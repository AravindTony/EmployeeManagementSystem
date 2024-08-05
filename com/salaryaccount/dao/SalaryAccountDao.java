package com.salaryaccount.dao;

import com.model.SalaryAccount;

/**
* This interface is for Salary Account Dao Implement 
* Database Operations
*/
public interface SalaryAccountDao {
 
    /**
    * This method insert the Salary Account Details to the 
    * Database
    * 
    * @param account - Salary Account details as the Object
    */
    public void insertSalaryAccount(SalaryAccount account);
}