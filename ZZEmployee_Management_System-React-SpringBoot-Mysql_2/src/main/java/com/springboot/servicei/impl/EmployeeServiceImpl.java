package com.springboot.servicei.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.dto.EmployeeDto;
import com.springboot.entity.Employee;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.mapper.EmployeeMapper;
import com.springboot.repository.EmployeeRepository;
import com.springboot.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
		.orElseThrow(() -> new ResourceNotFoundException("Employee is not Exists with given id." + employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
	List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
		
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		
		Employee employee =  employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee is not "
						+ "exists with given Id" + employeeId)
				);
		employee.setFirstname(updatedEmployee.getFirstName());
		employee.setLastname(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());
		Employee udatedEmployeeObj = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(udatedEmployeeObj);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee =  employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee is not "
						+ "exists with given Id" + employeeId)
				);
	employeeRepository.deleteById(employeeId);
		
	}
}
