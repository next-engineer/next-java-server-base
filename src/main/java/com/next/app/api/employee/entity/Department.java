package com.next.app.api.employee.entity;


import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Table(name = "department")
@Data
@AllArgsConstructor
@Builder
public class Department {
    
    private Long departmentId;

    private String name;
}
