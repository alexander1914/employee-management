package com.github.alexander.employee.service.ipml;

import com.github.alexander.employee.dto.DepartmentDto;
import com.github.alexander.employee.entity.Department;
import com.github.alexander.employee.exception.ResourceNotFoundException;
import com.github.alexander.employee.repository.DepartmentRepository;
import com.github.alexander.employee.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        // Mapping DepartmentDto to Department Entity
        Department department = modelMapper.map(departmentDto, Department.class);

        // Save Department Entity
        Department savedDepartment = departmentRepository.save(department);

        // Mapping the response to savedDepartmentDto for user.
        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        return modelMapper.map(department, DepartmentDto.class);
    }
}
