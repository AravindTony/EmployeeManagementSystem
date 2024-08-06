package com.ideas2it.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;

import com.ideas2it.model.Employee;

/** 
* <p>This class is used to get and set the 
* Information about the Mentor like Mentor Name
* and List of Employees </p>
* @author Aravind
*/

@Entity
@Table (name = "mentors")
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int mentorId;

    @Column (name = "name")
    private String mentorName;

    @Column (name = "is_deleted")
    boolean isDeleted = false;

    @ManyToMany (mappedBy = "mentors", fetch = FetchType.EAGER)
    Set<Employee> employees;

    /** 
    * This Constructor Initializes the new Mentor Id and Name
    *
    * @param name - Name of the Mentor
    */
    public Mentor(String mentorName) {
        this.mentorName = mentorName;
    }

    public Mentor() { }

    public int getMentorId() {
	return mentorId;
    }

    public String getMentorName() {
	return mentorName;
    }

    public boolean getIsDeleted() {
	return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
    }

    public void setName(String mentorName) {
        this.mentorName = mentorName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setMentorId(int mentorId) {
	this.mentorId = mentorId;
    }

    public void setMentorName(String mentorName) {
	this.mentorName = mentorName;
    }

    public void setEmployees(Set<Employee> employees) {
	this.employees = employees;
    }
}