package com.ideas2it.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;

/** 
* This class is used to get and set the 
* information about the Salary Account like
* @author Aravind 
*/

@Entity
@Table (name = "salaryaccount")
public class SalaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int accountId;

    @Column (name = "number")
    private long accountNumber;

    @Column (name = "name")
    private String bankName;

    @OneToOne (mappedBy = "account", fetch = FetchType.EAGER)
    private Employee employee;
    
    /** 
    * This Constructor Initialize a new name and list
    * of Employees while add the new Salary Account
    *
    * @param accountNumber - Account Number of the Salary Account
    * @param bankName - Name of the Bank
    */
    public SalaryAccount(String bankName, long accountNumber) {
	this.accountNumber = accountNumber;
	this.bankName = bankName;
    }

    public SalaryAccount() { }

    public int getAccountId() {
	return accountId;
    }

    public long getAccountNumber() {
	return accountNumber;
    }

    public String getBankName() {
	return bankName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
	this.employee = employee;
    }   

    public void setAccountId(int accountId) {
	this.accountId = accountId;
    }

    public void setAccountNumber(long accountNumber) {
	this.accountNumber = accountNumber;
    }

    public void setBankName(String bankName) {
	this.bankName = bankName;
    }
}