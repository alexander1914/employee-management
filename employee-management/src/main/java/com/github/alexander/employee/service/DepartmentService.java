package com.github.alexander.employee.service;

import com.github.alexander.employee.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto getDepartmentById(Long id);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id);
    void deleteDepartment(Long id);
}
