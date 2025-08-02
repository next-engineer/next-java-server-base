package com.next.app.api.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface hasLongevity extends JpaRepository<Employee, Long>{
    
    List<Employee> findByHireDateBefore(java.time.LocalDate date);
}
