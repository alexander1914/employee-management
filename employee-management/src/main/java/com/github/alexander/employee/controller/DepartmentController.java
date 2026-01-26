package com.github.alexander.employee.controller;

import com.github.alexander.employee.dto.DepartmentDto;
import com.github.alexander.employee.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> findDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);

        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departments = departmentService.getAllDepartments();

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.addDepartment(departmentDto);

        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto,
                                                          @PathVariable("id") Long departmentId){
        DepartmentDto updateDepartment = departmentService.updateDepartment(departmentDto, departmentId);

        return new ResponseEntity<>(updateDepartment, HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);

        return new ResponseEntity<>("Department deleted successfully", HttpStatus.OK);
    }
}
