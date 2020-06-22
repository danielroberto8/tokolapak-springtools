package com.cimb.tokolapak.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.tokolapak.dao.DepartmentRepo;
import com.cimb.tokolapak.dao.EmployeeRepo;
import com.cimb.tokolapak.entity.Department;
import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping
	public Iterable<Department> getDepartment(){
		return departmentService.getAllDepartments();
	}
	
	@PostMapping
	@Transactional
	public Department addDepartment(@RequestBody Department department) {
		department.setId(0);
		return departmentService.addDepartment(department);
	}
	
	@DeleteMapping("/delete/{deleteId}")
	public void deleteDepartmentById(@PathVariable int deleteId) {
		Department findDepartment = departmentRepo.findById(deleteId).get();
		findDepartment.getEmployee().forEach(employee -> {
			employee.setDepartment(null);
			employeeRepo.save(employee);
		});
	}
}
