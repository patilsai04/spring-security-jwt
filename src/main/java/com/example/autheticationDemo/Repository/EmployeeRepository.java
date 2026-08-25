package com.example.autheticationDemo.Repository;

import com.example.autheticationDemo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
