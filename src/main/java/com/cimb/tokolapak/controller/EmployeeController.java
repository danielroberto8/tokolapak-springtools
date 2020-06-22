package com.cimb.tokolapak.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.tokolapak.dao.DepartmentRepo;
import com.cimb.tokolapak.dao.EmployeeAddressRepo;
import com.cimb.tokolapak.dao.EmployeeRepo;
import com.cimb.tokolapak.dao.ProjectRepo;
import com.cimb.tokolapak.entity.Department;
import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.entity.EmployeeAddress;
import com.cimb.tokolapak.entity.Project;
import com.cimb.tokolapak.service.EmployeeService;
@RestController
@RequestMapping("/employee")
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private EmployeeAddressRepo employeeAddressRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@GetMapping
	public Iterable<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@PutMapping("{employeeId}/department/{departmentId}")
	public Employee addEmployeeDepartment(@PathVariable int departmentId, @PathVariable int employeeId ) {
		Employee findEmployee = employeeRepo.findById(employeeId).get();
		if(findEmployee == null) {
			throw new RuntimeException("Datanya tidak ada");
		}
		
		Department findDepartment = departmentRepo.findById(departmentId).get();
		
		
		if(findDepartment == null) {
			throw new RuntimeException("gaaduadiha vcaihvc");
		}
		
		findEmployee.setDepartment(findDepartment);
		return employeeRepo.save(findEmployee);
		
		
		/*return departmentRepo.findById(departmentId).map(department ->{
			findEmployee.setDepartment(department);
			return employeeRepo.save(findEmployee);
		}).orElseThrow(()->{throw new RuntimeException("Lieur");});*/
	}
	
	@PostMapping("/{employeeId}/projects/{projectId}")
	public Employee addEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
		Employee findEmployee = employeeRepo.findById(employeeId).get();
		if(findEmployee==null) {
			throw new RuntimeException("DATA EMPLOYEE TYDAC ADA");
		}
		
		Project findProject = projectRepo.findById(projectId).get();
		if(findProject==null) {
			throw new RuntimeException("DATA PROJECT TYDAC ADA");
		}
	
		findEmployee.getProjects().add(findProject);
		
		return employeeRepo.save(findEmployee);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployeeAddressById(@PathVariable int id) {
		Optional<EmployeeAddress> employeeAddress = employeeAddressRepo.findById(id);
		if(employeeAddress.get()==null) {
			throw new RuntimeException("Employee Address Not Found");
		}
		
		employeeService.deleteEmployeeAddress(employeeAddress.get());
	}
	
	@PutMapping("/{id}")
	public void updateEmployeeAddress(@PathVariable int id, @RequestBody Employee employee) {
		Optional<Employee> employeeData = employeeRepo.findById(id) ;
		if(employeeData.get()==null) {
			throw new RuntimeException("Employee Address Not Found");
		}
		
		employeeService.updateEmployeeAddress(id,employee);

	}
}
