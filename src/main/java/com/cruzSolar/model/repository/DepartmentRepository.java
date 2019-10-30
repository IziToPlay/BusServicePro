package com.cruzSolar.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cruzSolar.model.entity.Client;
import com.cruzSolar.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
}
