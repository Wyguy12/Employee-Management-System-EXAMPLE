package com.wyattfredrickson.javafullstackapplication.services;

import com.wyattfredrickson.javafullstackapplication.models.Employee;
import com.wyattfredrickson.javafullstackapplication.repositories.EmployeeRepository;
import com.wyattfredrickson.javafullstackapplication.utils.BinarySearchTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository; // The employee repository

    private BinarySearchTree<Long, Employee> employeeTree = new BinarySearchTree<>(); // The binary search tree for managing employees

    /**
     * Method to get all employees in the database
     * @return A list of all employees
     */
    public List<Employee> getAllEmployees() {
        return employeeTree.inorder(); // Return the inorder traversal of the binary search tree
    }

    /**
     * Method to get an employee by their ID
     * @param id The ID of the employee
     * @return The employee with the given ID
     */
    public Optional<Employee> getEmployeeById(Long id) {
        Employee employee = employeeTree.search(id); // Search for the employee in the binary search tree
        return Optional.ofNullable(employee); // Return the employee if it exists 
    }

    /**
     * Method to create an employee in the database
     * @param employee The employee to create
     * @return The created employee
     */
    public Employee createEmployee(Employee employee) {
        employeeTree.insert(employee.getId(), employee); // Insert the employee into the binary search tree
        return employeeRepository.save(employee); // Save the employee to the database
    }

    /**
     * Method to update an employee in the database
     * @param id The ID of the employee to update
     * @param updatedEmployee The updated employee
     * @return The updated employee
     */
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        // Update the employee in the Binary Search Tree data structure
        employeeTree.insert(id, updatedEmployee); // Insert the updated employee into the binary search tree

        // Update the employee in the database
        return employeeRepository.findById(id)
                .map(employee -> { // Map the employee to the updated employee
                    employee.setFirstName(updatedEmployee.getFirstName()); // Set the first name of the employee to the updated employee's first name
                    employee.setLastName(updatedEmployee.getLastName()); // Set the last name of the employee to the updated employee's last name
                    employee.setEmail(updatedEmployee.getEmail()); // Set the email of the employee to the updated employee's email
                    return employeeRepository.save(employee); // Save the updated employee to the database
                })
                .orElse(null); // Return null if the employee does not exist
    }

    /**
     * Method to delete an employee from the database
     * @param id The ID of the employee to delete
     */
    public void deleteEmployee(Long id) {
        employeeTree.delete(id); // Delete the employee from the binary search tree
        employeeRepository.deleteById(id); // Delete the employee from the database
    }
}
