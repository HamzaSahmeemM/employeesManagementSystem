package com.hsd.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.ems.model.Employee;
import com.hsd.ems.model.EmployeeNameAndIdDTO;
import com.hsd.ems.model.ReportingChainDTO;
import com.hsd.ems.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		try {
			return new ResponseEntity<Employee>(employeeService.createEmployee(employee), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Employee creation failed "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        try {
			return new ResponseEntity<Employee>(employeeService.updateEmployee(id,updatedEmployee), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Employee update failed "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
    }
	
	@PutMapping("/{employeeId}/moveToDepartment/{newDepartmentId}")
	public ResponseEntity<?> moveEmployeeToDepartment( @PathVariable Long employeeId,@PathVariable Long newDepartmentId) {
		try {
			employeeService.updateDepartmentOfEmployee(employeeId, newDepartmentId);
			return new ResponseEntity<String>("Employees department updated successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
	
	
	@GetMapping("/getEmployeeNamesAndIds")
    public ResponseEntity<List<EmployeeNameAndIdDTO>> listEmployeeNamesAndIds(@RequestParam(required = true) String lookup) {
		List<EmployeeNameAndIdDTO> eDto = employeeService.getEmployeesNameAndIds();
		return ResponseEntity.ok(eDto);
	}
      
	@GetMapping("/getAllWithPagable")
	public Page<Employee> getAllEmployees(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size) {
		return employeeService.getAllEmployees(page, size);
	}
	
	@GetMapping("/reportingChain/{id}")
    public ResponseEntity<?> getReportingChain(@PathVariable Long userId) {
        try {
        	ReportingChainDTO reportingChain = employeeService.getReportingChain(userId);
            return new ResponseEntity<>(reportingChain, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
        
    }
}
