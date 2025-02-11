package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
