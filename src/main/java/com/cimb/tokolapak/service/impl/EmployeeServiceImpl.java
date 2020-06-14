package com.cimb.tokolapak.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimb.tokolapak.dao.EmployeeAddressRepo;
import com.cimb.tokolapak.dao.EmployeeRepo;
import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.entity.EmployeeAddress;
import com.cimb.tokolapak.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeAddressRepo employeeAddressRepo;
	
	@Override
	@Transactional
	public Iterable<Employee> getEmployees(){
		return employeeRepo.findAll();
	}
	
	@Override
	@Transactional
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public void deleteEmployeeAddress(EmployeeAddress employeeAddress) {
		// TODO Auto-generated method stub
		employeeAddress.getEmployee().setEmployeeAddress(null);
		employeeAddress.setEmployee(null);
		employeeAddressRepo.delete(employeeAddress);
	}
	
	@Override
	public void updateEmployeeAddress(int id, Employee employee) {
		if(employee.getEmployeeAddress()==null) {
			employeeRepo.save(employee);
		}
	}
}
