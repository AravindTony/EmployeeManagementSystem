package com.ideas2it.salaryAccount.service;

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
    void addSalaryAccount(SalaryAccount account);
}