package com.example.autheticationDemo.Service;

import com.example.autheticationDemo.DTO.EmployeeRequestDTO;
import com.example.autheticationDemo.DTO.EmployeeResponseDTO;
import com.example.autheticationDemo.Entity.Employee;
import com.example.autheticationDemo.Execption.EmployeeNotFoundException;
import com.example.autheticationDemo.Mapper.EmployeeMapper;
import com.example.autheticationDemo.Repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    // get All Employee
    @PreAuthorize("hasAuthority('EMPLOYEE_READ')")
    public Page<EmployeeResponseDTO> getAllEmployee(Pageable pageable){
        Page<Employee> saved= employeeRepository.findAll(pageable);
        return saved.map(employeeMapper::toResponse);
    }

    // getEmployeeId
    public EmployeeResponseDTO getEmployeeById(int id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee not found"));
        return employeeMapper.toResponse(employee);
    }

    //addEmployee
    @PreAuthorize("hasAuthority('EMPLOYEE_CREATE')")
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO requestDTO){
       Employee employee = employeeMapper.toEntity(requestDTO);
       Employee savedEmployee = employeeRepository.save(employee);
       return employeeMapper.toResponse(savedEmployee);
    }

    //update Employee
    @PreAuthorize("hasAuthority('EMPLOYEE_UPDATE')")
    public EmployeeResponseDTO updateEmployee(int id, EmployeeRequestDTO requestDTO){
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));
        existingEmployee.setName(requestDTO.getName());
        existingEmployee.setSalary(requestDTO.getSalary());

        Employee savedEmployee = employeeRepository.save(existingEmployee);
        return employeeMapper.toResponse(savedEmployee);
    }

    // delete Employee
    @PreAuthorize("hasAuthority('EMPLOYEE_DELETE')")
    public void deleteById(int id){
        employeeRepository.deleteById(id);
    }
}
