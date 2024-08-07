package com.ideas2it.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

/** 
* This class is used to get and set the 
* information about the Department like
* Department id for Unique Departments
* Department Name and List of Employees in the Department
* @author Aravind 
*/

@Entity
@Table (name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int departmentId;

    @Column (name = "name")
    private String departmentName;

    @Column (name = "is_deleted")
    private boolean isDeleted = false;

    @OneToMany (mappedBy = "department", fetch = FetchType.EAGER)
    Set<Employee> employees;
    
    /** 
    * This Constructor Initialize a new name and list
    * of Employees while add the new Department
    *
    * @param departmentName - Name of the Department
    */
    public Department(String departmentName) {
	this.departmentName = departmentName;
    }

    public Department() { }

    public int getDepartmentId() {
	return departmentId;
    }

    public String getDepartmentName() {
	return departmentName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public boolean getIsDeleted() {
	return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
    }

    public void setEmployees(Set<Employee> employees) {
	this.employees = employees;
    }   

    public void setDepartmentId(int departmentId) {
	this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
    }
}