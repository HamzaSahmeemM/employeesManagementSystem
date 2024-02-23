package com.hsd.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hsd.ems.model.Department;
import com.hsd.ems.model.Employee;
import com.hsd.ems.model.EmployeeNameAndIdDTO;
import com.hsd.ems.model.InsufficientDataException;
import com.hsd.ems.model.ReportingChainDTO;
import com.hsd.ems.repository.DepartmetRepository;
import com.hsd.ems.repository.EmployeeReporsitory;

@Service
public class EmployeeService {
	
	@Autowired 
	private EmployeeReporsitory employeeReporsitory;
	
	@Autowired
	private DepartmetRepository departmetRepository;
	
	/**
	 * Creating employee
	 * @param employee
	 * @return
	 */
	public Employee createEmployee(Employee employee) {
		
		if(employee.getRole() == null) {
			throw new InsufficientDataException("Employee role is missing");
		} else if( employee.getRole() != null  && !employee.getRole().equalsIgnoreCase("admin")) {
			if(employee.getDepartmentId() == null) {
				throw new InsufficientDataException("Department is empty ");
			}
			else {
				Optional<Department> oDep = departmetRepository.findById(employee.getDepartmentId());
				if(!oDep.isPresent()) {
					throw new InsufficientDataException("Department "+employee.getDepartmentId() +"is  not found");
				}
			}
			
			if(employee.getReportingManagerId() == null) {
				throw new InsufficientDataException("Reporting manager is empty ");
			}else {
				Optional<Employee> oDep = employeeReporsitory.findById(employee.getReportingManagerId());
				if(!oDep.isPresent()) {
					throw new InsufficientDataException("Manager with id "+employee.getReportingManagerId() +" is  not found");
				}
			}
			
		}
		return employeeReporsitory.save(employee);
	}
	
	/**
	 * Updating employee
	 * @param id
	 * @param employee
	 * @return
	 */
	public Employee updateEmployee(Long id,Employee employee) {
		Optional<Employee> oemp = employeeReporsitory.findById(id);
		if(!oemp.isPresent()) {
			throw new InsufficientDataException("Employee not found with id "+ id);
		}
		employee.setId(id);
		return createEmployee(employee);
	}
	
	public void updateDepartmentOfEmployee(Long empid, Long depId) {
		Optional<Employee> oemp = employeeReporsitory.findById(empid);
		
		if(!oemp.isPresent()) {
			throw new InsufficientDataException("Employee not found with id "+empid);
		}
		
		Optional<Department> odep = departmetRepository.findById(depId);
		if(!odep.isPresent()) {
			throw new InsufficientDataException("Departmemt not found with id "+depId);
		}
		
		Employee e = oemp.get();
		
		e.setDepartmentId(depId);
		
		createEmployee(e);
	}
	
	public List<Employee> getAllEmployees(){
		return employeeReporsitory.findAll();
	}
	
	public List<EmployeeNameAndIdDTO> getEmployeesNameAndIds(){
		List<Employee> employees = getAllEmployees();
		List<EmployeeNameAndIdDTO> eDto = new ArrayList<>();
		
		for(Employee e:employees) {
			EmployeeNameAndIdDTO ed = new EmployeeNameAndIdDTO();
			ed.setId(e.getId());
			ed.setName(e.getName());
			eDto.add(ed);
		}
		return eDto;
	}
	
	public Page<Employee> getAllEmployees(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size);
		return employeeReporsitory.findAll(pageable);
	}
	
	// Method to get reporting chain for an employee
    public ReportingChainDTO getReportingChain(Long employeeId) {
        
    	Optional<Employee> oEmp = employeeReporsitory.findById(employeeId);
    	
    	if(!oEmp.isPresent()) {
    		throw new InsufficientDataException("User with id "+employeeId+" is not found");
    	}
    	
    	Employee emp = oEmp.get();
    	ReportingChainDTO reportingChain = new ReportingChainDTO();
    	
    	List<Employee> reportingEmployees = new ArrayList<>();
        Employee currentManager = null;
        
    	if(emp.getReportingManagerId() != null) {
    		Optional<Employee> e = employeeReporsitory.findById(emp.getReportingManagerId());
    		reportingChain.setManager(e.get());
    		currentManager = e.get();
    	}

        while (currentManager != null && currentManager.getReportingManagerId() != null) {
            reportingEmployees.add(currentManager);
            Optional<Employee> e = employeeReporsitory.findById(currentManager.getReportingManagerId());
            if(e.isPresent()) {
            	currentManager = e.get();
            }else {
            	currentManager = null;
            }
            
        }
        reportingChain.setReportingEmployees(reportingEmployees);

        return reportingChain;
    }
}
