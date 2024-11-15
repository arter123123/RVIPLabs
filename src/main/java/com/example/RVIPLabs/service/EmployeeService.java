package com.example.RVIPLabs.service;

import com.example.RVIPLabs.model.Employee;
import com.example.RVIPLabs.model.EmploymentStatus;
import com.example.RVIPLabs.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee hire(Employee employee) {
        employee.setEmploymentStatus(EmploymentStatus.ACTIVE);
        return repository.save(employee);
    }

    public Employee terminate(Long id, String reason) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setEmploymentStatus(EmploymentStatus.TERMINATED);
        employee.setTerminationReason(reason);
        return repository.save(employee);
    }

    public Employee updateSalary(Long id, Double newSalary) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setSalary(newSalary);
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setName(updatedEmployee.getName());
        employee.setPosition(updatedEmployee.getPosition());
        employee.setSalary(updatedEmployee.getSalary());
        return repository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        repository.deleteById(id);
    }

    public long countActive() {
        return repository.countByEmploymentStatus(EmploymentStatus.ACTIVE);
    }

    public long countTerminated() {
        return repository.countByEmploymentStatus(EmploymentStatus.TERMINATED);
    }
}