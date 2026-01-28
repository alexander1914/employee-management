package com.github.alexander.employee.service;

import com.github.alexander.employee.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto addEmployee(Long departmentId, EmployeeDto employeeDto);
}
