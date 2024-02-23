package com.hsd.ems.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.ems.model.Department;
import com.hsd.ems.model.DepartmentDTO;
import com.hsd.ems.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/createDepartment")
    public ResponseEntity<?> addDepartment(@RequestBody Department newDepartment) {
		try {
			Department savedDepartment = departmentService.createDepartment(newDepartment);
	        return ResponseEntity.ok(savedDepartment);
		} catch (Exception e) {
			return new ResponseEntity<String>("Department creation failed "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
        
    }
	
	@PutMapping("/updateDepartment/{id}")
    public ResponseEntity<?> addDepartment(@PathVariable Long id,@RequestBody Department newDepartment) {
		try {
			Department savedDepartment = departmentService.updateDepartment(id,newDepartment);
	        return ResponseEntity.ok(savedDepartment);
		} catch (Exception e) {
			return new ResponseEntity<String>("Department creation failed "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
    }
	
	@DeleteMapping("/delete/{departmentId}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Long departmentId) {
		try {
			if(departmentService.deleteDepartment(departmentId)) {
				return new ResponseEntity<String>("Department deleted", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Department failed", HttpStatus.EXPECTATION_FAILED);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Department>> getAllDepartments() {
		List<Department> departments = departmentService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}
	
	@GetMapping("/getWithEmployees/{departmentId}")
    public ResponseEntity<?> getDepartmentWithEmployees(@PathVariable Long departmentId,@RequestParam(required = false) String expand) {
		try {
			return new ResponseEntity<DepartmentDTO>(departmentService.getWithEmployees(departmentId, expand), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/getAllWithPagable")
	public Page<Department> getAllEmployees(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size) {
		return departmentService.getAllDepartments(page, size);
	}
	
	@GetMapping("/getSummary")
    public ResponseEntity<Map<String, Long>> getTotals() {
        Map<String, Long> counts = departmentService.getSummary();
        return ResponseEntity.ok(counts);
    }
	
	@GetMapping("/getDepartmentAndEmployeeCounts")
    public ResponseEntity<List<Map<String, Long>>> getDepartmentAndEmployeeCounts() {
		List<Map<String, Long>> counts = departmentService.getDepartmentAndEmployeeCounts();
        return ResponseEntity.ok(counts);
    }
	
	
}
