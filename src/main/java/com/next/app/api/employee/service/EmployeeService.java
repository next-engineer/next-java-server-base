package com.next.app.api.employee.service;

import com.next.app.api.employee.entity.Employee;
import com.next.app.api.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    public List<Employee> getCurrentEmployees() {
        return employeeRepository.findByHireDateBefore(LocalDate.now());
    }
    
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }
} 