package com.wyattfredrickson.javafullstackapplication.controller;


import com.wyattfredrickson.javafullstackapplication.models.Employee;
import com.wyattfredrickson.javafullstackapplication.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This class is a REST controller
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired // Inject the EmployeeService
    private EmployeeService employeeService;

    @GetMapping // Handle GET requests to /api/employees
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}") // Handle GET requests to /api/employees/{id}
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // Handle POST requests to /api/employees 
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}") // Handle PUT requests to /api/employees/{id}
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeService.updateEmployee(id, updatedEmployee);
        return (employee != null) ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}") // Handle DELETE requests to /api/employees/{id}
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build(); 
    }
}
