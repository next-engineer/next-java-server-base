package com.next.app.api.employee.controller;

import com.next.app.api.employee.entity.Employee;
import com.next.app.api.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee", description = "사원 관리 API")
public class EmployeeController {
    
    private final EmployeeService employeeService;

    @GetMapping("/active")
    @Operation(summary = "현재 사원 조회", description = "현재까지 입사한 사원들을 조회합니다.")
    public List<Employee> getActiveEmployees() {
        return employeeService.getCurrentEmployees();
    }

    @PostMapping
    @Operation(summary = "사원 등록", description = "새로운 사원을 등록합니다.")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }
    
    @GetMapping
    @Operation(summary = "모든 사원 조회", description = "전체 사원 목록을 조회합니다.")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }
} 