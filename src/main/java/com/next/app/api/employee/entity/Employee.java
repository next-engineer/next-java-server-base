package com.next.app.api.employee.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "department_id", nullable = false)
    private Long departmentId;
    
    @Column(name = "position", nullable = false, length = 50)
    private String position;
    
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;
} 