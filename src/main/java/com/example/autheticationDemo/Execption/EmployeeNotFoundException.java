package com.example.autheticationDemo.Execption;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String msg){
        super(msg);
    }
}
