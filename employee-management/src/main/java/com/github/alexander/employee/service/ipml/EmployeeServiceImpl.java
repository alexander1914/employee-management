package com.github.alexander.employee.service.ipml;

import com.github.alexander.employee.dto.DepartmentDto;
import com.github.alexander.employee.dto.EmployeeDto;
import com.github.alexander.employee.entity.Department;
import com.github.alexander.employee.entity.Employee;
import com.github.alexander.employee.exception.ResourceNotFoundException;
import com.github.alexander.employee.repository.DepartmentRepository;
import com.github.alexander.employee.repository.EmployeeRepository;
import com.github.alexander.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository,
                               ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto addEmployee(Long departmentId, EmployeeDto employeeDto) {
        // First, we'll retrieve the Department from the database using the given department ID.
        // If the department does not exist, we'll throw a ResourceNotFoundException.
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        //Second, we'll convert the EmployeDTO object into Employee JPA Entity.
        Employee employee =modelMapper.map(employeeDto, Employee.class);

        //Third we'll associate the Employee entity with the retrieve Department entity.
        employee.setDepartment(department);

        //Fourth, we'll save this Employee entity into the database.
        Employee saveEmployee = employeeRepository.save(employee);

        //And finally, we'll convert the saved Employee entity back in an Employee object before returning it.
        EmployeeDto savedEmployeeDto = modelMapper.map(saveEmployee, EmployeeDto.class);
        savedEmployeeDto.setDepartmentId(departmentId);

        return savedEmployeeDto;
    }
}
