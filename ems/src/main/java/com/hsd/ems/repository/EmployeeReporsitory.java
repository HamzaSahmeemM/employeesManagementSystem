package com.hsd.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsd.ems.model.Employee;

@Repository
public interface EmployeeReporsitory extends JpaRepository<Employee, Long>{
	
    public Boolean existsByDepartmentId(Long departmentId);
    
    public List<Employee> getEmployeesByDepartmentId(Long departmentId);

}

