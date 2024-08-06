package com.ideas2it.salaryaccount.service;

import com.ideas2it.model.SalaryAccount;
import com.ideas2it.salaryaccount.dao.SalaryAccountDao;
import com.ideas2it.salaryaccount.dao.SalaryAccountDaoImpl;

public class SalaryAccountServiceImpl implements SalaryAccountService {
    
    SalaryAccountDao salaryAccountDao = new SalaryAccountDaoImpl();
    public void addSalaryAccount(SalaryAccount account) {
	salaryAccountDao.insertSalaryAccount(account);
    }
}