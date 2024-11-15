package com.example.RVIPLabs.repository;

import com.example.RVIPLabs.model.Employee;
import com.example.RVIPLabs.model.EmploymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    long countByEmploymentStatus(EmploymentStatus employmentStatus);
}