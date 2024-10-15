package com.wyattfredrickson.javafullstackapplication.repositories;



import com.wyattfredrickson.javafullstackapplication.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // EmployeeRepository is a repository that manages Employee entities in the database
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
