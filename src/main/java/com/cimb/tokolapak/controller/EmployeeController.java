package com.cimb.tokolapak.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.tokolapak.dao.EmployeeAddressRepo;
import com.cimb.tokolapak.dao.EmployeeRepo;
import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.entity.EmployeeAddress;
import com.cimb.tokolapak.service.EmployeeService;
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private EmployeeAddressRepo employeeAddressRepo;
	
	@GetMapping("/employee")
	public Iterable<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployeeAddressById(@PathVariable int id) {
		Optional<EmployeeAddress> employeeAddress = employeeAddressRepo.findById(id);
		if(employeeAddress.get()==null) {
			throw new RuntimeException("Employee Address Not Found");
		}
		
		employeeService.deleteEmployeeAddress(employeeAddress.get());
	}
	
	@PutMapping("/employee/{id}")
	public void updateEmployeeAddress(@PathVariable int id, @RequestBody Employee employee) {
		Optional<Employee> employeeData = employeeRepo.findById(id) ;
		if(employeeData.get()==null) {
			throw new RuntimeException("Employee Address Not Found");
		}
		
		employeeService.updateEmployeeAddress(id,employee);

	}
}
