package com.example.autheticationDemo.Controller;

import com.example.autheticationDemo.DTO.EmployeeRequestDTO;
import com.example.autheticationDemo.DTO.EmployeeResponseDTO;
import com.example.autheticationDemo.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Page<EmployeeResponseDTO> getAllEmployee(Pageable pageable){
        return employeeService.getAllEmployee(pageable);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeResponseDTO addEmployee(@RequestBody EmployeeRequestDTO requestDTO){
        return employeeService.addEmployee(requestDTO);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeRequestDTO requestDTO){
        return employeeService.updateEmployee(id,requestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id){
        employeeService.deleteById(id);
        return "Employee Delete SuccessFully";
    }
}
