package com.github.alexander.employee.controller;

import com.github.alexander.employee.dto.EmployeeDto;
import com.github.alexander.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/{departmentId}/employees")
    public ResponseEntity<EmployeeDto> addEmployee(@PathVariable("departmentId") Long departmentId,
                                                   @RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployee = employeeService.addEmployee(departmentId, employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
}
