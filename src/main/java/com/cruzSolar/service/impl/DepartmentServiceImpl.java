package com.cruzSolar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cruzSolar.model.entity.Department;
import com.cruzSolar.model.repository.DepartmentRepository;
import com.cruzSolar.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAll() throws Exception {
		return departmentRepository.findAll();
	}

	@Override
	public Long create(Department entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long id, Department entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department getOneById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
