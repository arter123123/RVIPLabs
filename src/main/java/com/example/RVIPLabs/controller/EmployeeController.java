package com.example.RVIPLabs.controller;

import com.example.RVIPLabs.model.Employee;
import com.example.RVIPLabs.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;
    // Конструктор для внедрения зависимости
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @PostMapping("/hire")
    public ResponseEntity<Employee> hire(@RequestBody Employee employee) {
        return ResponseEntity.ok(service.hire(employee));
    }

    @PostMapping("/terminate/{id}")
    public ResponseEntity<Employee> terminate(@PathVariable Long id, @RequestBody String reason) {
        return ResponseEntity.ok(service.terminate(id, reason));
    }

    @PutMapping("/salary/{id}")
    public ResponseEntity<Employee> updateSalary(@PathVariable Long id, @RequestBody Double newSalary) {
        return ResponseEntity.ok(service.updateSalary(id, newSalary));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return ResponseEntity.ok(service.updateEmployee(id, updatedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Long>> report() {
        Map<String, Long> report = new HashMap<>();
        report.put("active", service.countActive());
        report.put("terminated", service.countTerminated());
        return ResponseEntity.ok(report);
    }
}
