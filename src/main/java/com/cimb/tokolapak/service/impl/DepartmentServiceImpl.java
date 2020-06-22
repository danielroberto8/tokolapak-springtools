package com.cimb.tokolapak.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimb.tokolapak.dao.DepartmentRepo;
import com.cimb.tokolapak.entity.Department;
import com.cimb.tokolapak.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Override
	public Iterable<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentRepo.findAll();
	}

	@Override
	public Department addDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentRepo.save(department);
	}

}
