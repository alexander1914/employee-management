package com.github.alexander.employee.service;

import com.github.alexander.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployeeById(Long departmentId, Long employeeId);
    List<EmployeeDto> getAllEmployeesDepartmentId(Long departmentId);
    EmployeeDto addEmployee(Long departmentId, EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long departmentId, Long employeeId, EmployeeDto employeeDto);
}
