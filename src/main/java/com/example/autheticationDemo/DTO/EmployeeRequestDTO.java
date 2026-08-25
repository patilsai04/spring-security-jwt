package com.example.autheticationDemo.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequestDTO {
    @NotBlank(message ="Name will not be black" )
    private String name;

    @Min(value = 0,message = "Salary will not be less that 0")
    private double salary;

    public EmployeeRequestDTO() {
    }

    public EmployeeRequestDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
