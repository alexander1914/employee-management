package com.github.alexander.employee.service.ipml;

import com.github.alexander.employee.dto.DepartmentDto;
import com.github.alexander.employee.entity.Department;
import com.github.alexander.employee.repository.DepartmentRepository;
import com.github.alexander.employee.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        // Mapping DepartmentDto to Department Entity
        Department department = new Department();
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());

        // Save Department Entity
        Department savedDepartment = departmentRepository.save(department);

        // Mapping the response to savedDepartmentDto for user.
        DepartmentDto savedDepartmentDto = new DepartmentDto();
        savedDepartmentDto.setId(savedDepartment.getId());
        savedDepartmentDto.setDepartmentName(savedDepartment.getDepartmentDescription());
        savedDepartmentDto.setDepartmentDescription(savedDepartment.getDepartmentName());

        return savedDepartmentDto;
    }
}
