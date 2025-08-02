package com.next.app.api.employee.repository;


import java.time.LocalDate;

import org.springframework.data.repository.cdi.Eager;

import com.next.app.api.employee.entity.Department;


import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Eager
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "name", length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;;

    @Column(length = 50)
    private String position;

    private LocalDate hireDate;

    public boolean hasLongevity() {
        return hireDate != null && hireDate.isBefore(LocalDate.now().plusDays(1));
    }
}