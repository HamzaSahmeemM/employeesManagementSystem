package com.hsd.ems.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hsd.ems.model.Department;

@Repository
public interface DepartmetRepository extends JpaRepository<Department, Long>{
	
	@Query(value = "SELECT " + "(SELECT COUNT(*) FROM department) AS department_count, "
			+ "(SELECT COUNT(*) FROM employee) AS employee_count", nativeQuery = true)
	public Map<String, Long> getSummary();
	
	@Query(value = "SELECT " + "d.name AS department_name, COUNT(e.id) AS employee_count FROM department d "
			+ "LEFT JOIN employee e ON d.id = e.department_id GROUP BY d.id, d.name", nativeQuery = true)
	public List<Map<String, Long>> getDepartmentAndEmployeeCounts();
}
