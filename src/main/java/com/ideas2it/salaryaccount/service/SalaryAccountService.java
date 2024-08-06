package com.ideas2it.salaryaccount.service;

import com.ideas2it.model.SalaryAccount;

/**
 * This interface handles the abstract methods for 
 * Salary Account like insert salary Account to Database
 *
*/
public interface SalaryAccountService {
    
    /**
     * This method add Salary Account to the Database
     * 
     * @param account - Salary Account as Object
     *
     */
    public void addSalaryAccount(SalaryAccount account);
}