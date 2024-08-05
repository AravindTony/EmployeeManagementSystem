package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.model.Department;
import com.model.Mentor;
import com.model.SalaryAccount;

/**
* <p>This class is used to get and set the 
* Employee credentials like Employee Id
* Name of the Employee, Date of birth
* Mobile Number, Email, Qualification
* Experience and List of Mentors for the Employee</p>
* @author Aravind
*/

@Entity
@Table (name = "employees")
public class Employee {		

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int employeeId;	
    
    @Column (name = "name")					    
    private String employeeName;

    @Column (name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column (name = "mobile")
    private long mobileNumber;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "departmentId")
    private Department department;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId")
    private SalaryAccount account;

    @Column (name = "email")
    private String employeeEmail;

    @Column (name = "qualification")
    private String qualification;

    @Column (name = "experience")
    private int experience;

    @Column (name = "is_deleted")
    public boolean isDeleted = false;
   
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "association",
	joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "mentor_id"))
    private Set<Mentor> mentors; 

    /** 
    * Employee Constructor initializes the Credentials when add new Record
    *
    * @param employeeName - Name of the Employee
    * @param dateOfBirth - Date of Birth of the Employee
    * @param mobileNumber - Mobile Number of the Employee
    * @param employeeEmail - Email Id of the Employee
    * @param qualification - Qualification of the Employee
    * @param experience - Experience of the Employee Totally
    * @param department - Department as an Object
    * @param account - Account as the Salary Account Object
    */
    public Employee(String employeeName, Department department,            
	    LocalDate dateOfBirth, long mobileNumber, String employeeEmail, 
	    String qualification, int experience, SalaryAccount account) {
	this.employeeName = employeeName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
	this.employeeEmail = employeeEmail;
	this.qualification = qualification;
	this.experience = experience;
	this.department = department;
	this.account = account;
    }

    public Employee() { }

    public String getNames() {
        StringBuilder mentorList = new StringBuilder();
	for (Mentor mentor : mentors) {
	    mentorList.append(mentor.getMentorName() + ",");
        }	
	return mentorList.toString();
    }

    public Set<Mentor> getMentors() {
        return mentors;
    }
    
    public int getEmployeeId() {                                                  
	return employeeId;
    }
    
    public String getEmployeeName() {	
	return employeeName;
    }

    public Department getDepartment() {
	return department;
    }

    public SalaryAccount getAccount() {
	return account;
    }

    public LocalDate getDateOfBirth() {
	return dateOfBirth;
    }
    
    public long getMobileNumber() {
        return mobileNumber;
    }
    
    public String getEmployeeEmail() {
        return employeeEmail;
    }
 
    public String getQualification() {
	return qualification;
    }
    
    public int getExperience() {
	return experience;
    }

    public boolean getIsDeleted() {
	return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
    }

    public void setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {                              
	this.employeeName = employeeName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public void setMobileNumber(long mobileNumber) {
	this.mobileNumber = mobileNumber;
    }

    public void setEmployeeEmail(String employeeEmail) {
	this.employeeEmail = employeeEmail;
    }

    public void setQualification(String qualification) {
	this.qualification = qualification;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
 
    public void setDepartment(Department department) {
	this.department = department;
    }

    public void setAccount(SalaryAccount account) {
	this.account = account;
    }

    public void setMentors(Set<Mentor> mentors) {
	this.mentors = mentors;
    }

    public int getAge() {
	if (dateOfBirth != null) {
	    return Period.between(dateOfBirth, LocalDate.now()).getYears();
	}
	return 0;
    }
}