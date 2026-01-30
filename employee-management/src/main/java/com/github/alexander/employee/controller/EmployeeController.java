package com.github.alexander.employee.controller;

import com.github.alexander.employee.dto.EmployeeDto;
import com.github.alexander.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/{departmentId}/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long departmentId,
                                                       @PathVariable("id") Long employeeId){

        EmployeeDto employeeDto = employeeService.getEmployeeById(departmentId, employeeId);

        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesByDepartmentId(@PathVariable Long departmentId){

        List<EmployeeDto> employees = employeeService.getAllEmployeesDepartmentId(departmentId);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/{departmentId}/employees")
    public ResponseEntity<EmployeeDto> addEmployee(@PathVariable Long departmentId,
                                                   @RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployee = employeeService.addEmployee(departmentId, employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId,
                                                      @RequestBody EmployeeDto employeeDto){

        EmployeeDto updatedEmployee = employeeService.updateEmployee(departmentId, employeeId, employeeDto);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}
