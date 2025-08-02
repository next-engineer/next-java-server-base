package com.next.app.api.employee.repository;

import com.next.app.api.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // 입사일 이전 사원 조회
    List<Employee> findByHireDateBefore(LocalDate date);
    
    // 부서별 사원 조회
    List<Employee> findByDepartmentId(Long departmentId);
    
    // 직급별 사원 조회
    List<Employee> findByPosition(String position);
    
    // 이름으로 사원 검색
    List<Employee> findByNameContaining(String name);
} 