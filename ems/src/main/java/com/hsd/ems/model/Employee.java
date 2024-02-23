package com.hsd.ems.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private Double salary;
    private Long departmentId;
    private String address;
    private String role;
    private Date joiningDate;
    private Double yearlyBonusPercentage;
    private Long reportingManagerId;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Double getYearlyBonusPercentage() {
		return yearlyBonusPercentage;
	}
	public void setYearlyBonusPercentage(Double yearlyBonusPercentage) {
		this.yearlyBonusPercentage = yearlyBonusPercentage;
	}
	public Long getReportingManagerId() {
		return reportingManagerId;
	}
	public void setReportingManagerId(Long reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", salary=" + salary
				+ ", departmentId=" + departmentId + ", address=" + address + ", role=" + role + ", joiningDate="
				+ joiningDate + ", yearlyBonusPercentage=" + yearlyBonusPercentage + ", reportingManagerId="
				+ reportingManagerId + "]";
	}
    
}
