package com.hsd.ems.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hsd.ems.model.Department;
import com.hsd.ems.model.DepartmentDTO;
import com.hsd.ems.model.Employee;
import com.hsd.ems.model.InsufficientDataException;
import com.hsd.ems.repository.DepartmetRepository;
import com.hsd.ems.repository.EmployeeReporsitory;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmetRepository departmetRepository;
	@Autowired
	private EmployeeReporsitory employeeReporsitory; 
	
	public Department createDepartment(Department department) {

		if (department.getDepartmentHeadId() == null) {
			throw new InsufficientDataException("Department head is empty");
		} else {
			Optional<Employee> oEmp = employeeReporsitory.findById(department.getDepartmentHeadId());
			if (!oEmp.isPresent()) {
				throw new InsufficientDataException("Department head with id " + department.getDepartmentHeadId() + " not found");
			}
		}

		return departmetRepository.save(department);
	}
	
	public Department updateDepartment(Long id,Department dep) {
		Optional<Department> oDep = departmetRepository.findById(id);
		if(!oDep.isPresent()) {
			throw new InsufficientDataException("Department not found with id "+ id);
		}
		dep.setId(id);
		return createDepartment(dep);
	}
	
	public Boolean deleteDepartment(Long id) {
		
		Optional<Department> oDep = departmetRepository.findById(id);
		if(!oDep.isPresent()) {
			throw new InsufficientDataException("Department with id "+id +"is not found");
		}
		
		if(employeeReporsitory.existsByDepartmentId(id)) {
			throw new InsufficientDataException("Cant process deletion .Employees under Department with id "+id +"is not empty");
		}
		
		departmetRepository.deleteById(id);
		return true;
	}
	
	public List<Department> getAllDepartments(){
	    return departmetRepository.findAll();
	}
	
	public DepartmentDTO getWithEmployees(Long id, String expand) {

		Optional<Department> odep = departmetRepository.findById(id);
		if (!odep.isPresent()) {
			throw new InsufficientDataException("Department with id " + id + " is not found");
		}

		Department dep = odep.get();

		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setId(dep.getId());
		departmentDTO.setName(dep.getName());

		if (expand.equalsIgnoreCase("expand")) {
			departmentDTO.setEmployees(employeeReporsitory.getEmployeesByDepartmentId(id));
		}

		return departmentDTO;

	}
	
	public Page<Department> getAllDepartments(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size);
		return departmetRepository.findAll(pageable);
	}
	
	public Map<String, Long> getSummary(){
		return departmetRepository.getSummary();
	}
	
	public List<Map<String, Long>> getDepartmentAndEmployeeCounts(){
		return departmetRepository.getDepartmentAndEmployeeCounts();
	}
	
	
}
