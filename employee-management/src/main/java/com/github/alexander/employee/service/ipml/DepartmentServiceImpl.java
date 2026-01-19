package com.github.alexander.employee.service.ipml;

import com.github.alexander.employee.dto.DepartmentDto;
import com.github.alexander.employee.entity.Department;
import com.github.alexander.employee.exception.ResourceNotFoundException;
import com.github.alexander.employee.repository.DepartmentRepository;
import com.github.alexander.employee.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        // Find department by ID on database
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        // Find all departments on database
        List<Department> departments = departmentRepository.findAll();

        // Mapping Departments Entity to Departments DTOs
        return departments.stream()
                .map((department) -> modelMapper.map(departments,DepartmentDto.class))
                .collect(Collectors.toList());
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
}
