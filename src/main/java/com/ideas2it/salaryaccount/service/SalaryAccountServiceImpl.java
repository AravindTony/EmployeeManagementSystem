package com.ideas2it.salaryAccount.service;

import com.ideas2it.model.SalaryAccount;
import com.ideas2it.salaryAccount.dao.SalaryAccountDao;
import com.ideas2it.salaryAccount.dao.SalaryAccountDaoImpl;

public class SalaryAccountServiceImpl implements SalaryAccountService {
    SalaryAccountDao salaryAccountDao = new SalaryAccountDaoImpl();

    public void addSalaryAccount(SalaryAccount account) {
	salaryAccountDao.insertSalaryAccount(account);
    }
}