package com.github.alexander.employee.service.ipml;

import com.github.alexander.employee.dto.EmployeeDto;
import com.github.alexander.employee.entity.Department;
import com.github.alexander.employee.entity.Employee;
import com.github.alexander.employee.exception.BadRequestException;
import com.github.alexander.employee.exception.ResourceNotFoundException;
import com.github.alexander.employee.repository.DepartmentRepository;
import com.github.alexander.employee.repository.EmployeeRepository;
import com.github.alexander.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public EmployeeDto getEmployeeById(Long departmentId, Long employeeId) {
        // First, we'll retrieve the Department and Employee from the database using the given department ID.
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        //Second, it's validation the department from the database
        if(!employee.getDepartment().getId().equals(department.getId())){
            throw new BadRequestException("This employee does not belong to department with ID " + departmentId);
        }

        //Finally, We'll convert the saved Employee entity back in an Employee object before returning it.
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        employeeDto.setDepartmentId(employee.getDepartment().getId());

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployeesDepartmentId(Long departmentId) {
        // First, we'll retrieve the Department from the database using the given department ID.
        // If the department does not exist, we'll throw a ResourceNotFoundException.
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        //Second, we'll retrieve the Department from the database using the given department ID.
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);

        //Finally, We'll convert the saved Employee entity back in an Employee object before returning it.
        return employees.stream()
                .map((employee) -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto addEmployee(Long departmentId, EmployeeDto employeeDto) {
        // First, we'll retrieve the Department from the database using the given department ID.
        // If the department does not exist, we'll throw a ResourceNotFoundException.
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        //Second, we'll convert the EmployeDTO object into Employee JPA Entity.
        Employee employee =modelMapper.map(employeeDto, Employee.class);

        //Third, we'll associate the Employee entity with the retrieve Department entity.
        employee.setDepartment(department);

        //Fourth, we'll save this Employee entity into the database.
        Employee saveEmployee = employeeRepository.save(employee);

        //Finally, we'll convert the saved Employee entity back in an Employee object before returning it.
        EmployeeDto savedEmployeeDto = modelMapper.map(saveEmployee, EmployeeDto.class);
        savedEmployeeDto.setDepartmentId(departmentId);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(Long departmentId, Long employeeId, EmployeeDto employeeDto) {
        // First, we'll retrieve the Department and Employee from the database using the given department ID.
        // If the department does not exist, we'll throw a ResourceNotFoundException.
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        //Second, it's validation the department from the database
        if(!employee.getDepartment().getId().equals(department.getId())){
            throw new BadRequestException("This employee does not belong to department with id: " + departmentId);
        }

        //Third, mapping EmployeeDto to Employee Entity
        employee.setFirstname(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        //Fourth, We'll save this Employee entity into the database.
        Employee updatedEmployee = employeeRepository.save(employee);

        //Finally, we'll convert the saved Employee entity back in an Employee object before returning it.
        EmployeeDto savedEmployeeDto = modelMapper.map(updatedEmployee, EmployeeDto.class);
        savedEmployeeDto.setDepartmentId(departmentId);

        return savedEmployeeDto;
    }
}
