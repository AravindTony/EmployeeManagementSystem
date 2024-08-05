package com.salaryaccount.service;

import com.model.SalaryAccount;
import com.salaryaccount.dao.SalaryAccountDao;
import com.salaryaccount.dao.SalaryAccountDaoImpl;

public class SalaryAccountServiceImpl implements SalaryAccountService {
    
    SalaryAccountDao salaryAccountDao = new SalaryAccountDaoImpl();
    public void addSalaryAccount(SalaryAccount account) {
	salaryAccountDao.insertSalaryAccount(account);
    }
}