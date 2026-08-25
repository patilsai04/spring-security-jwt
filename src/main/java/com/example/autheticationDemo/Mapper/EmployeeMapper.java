package com.example.autheticationDemo.Mapper;

import com.example.autheticationDemo.DTO.EmployeeRequestDTO;
import com.example.autheticationDemo.DTO.EmployeeResponseDTO;
import com.example.autheticationDemo.Entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeRequestDTO requestDTO);
    EmployeeResponseDTO toResponse(Employee employee);
}
