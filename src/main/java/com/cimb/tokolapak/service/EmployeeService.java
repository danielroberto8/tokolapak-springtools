package com.cimb.tokolapak.service;

import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.entity.EmployeeAddress;

public interface EmployeeService {
	public Iterable<Employee> getEmployees();
	
	public Employee addEmployee(Employee employee);
	
	public default void deleteEmployeeAddress(EmployeeAddress employeeAddress) {
		
	}
	
	public default void updateEmployeeAddress(int id, Employee employee) {
		
	}
}
