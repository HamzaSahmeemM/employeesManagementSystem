package com.hsd.ems.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Department {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private Long departmentHeadId;
	
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
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getDepartmentHeadId() {
		return departmentHeadId;
	}
	public void setDepartmentHeadId(Long departmentHeadId) {
		this.departmentHeadId = departmentHeadId;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", creationDate=" + creationDate + ", departmentHeadId="
				+ departmentHeadId + "]";
	}
    
}
