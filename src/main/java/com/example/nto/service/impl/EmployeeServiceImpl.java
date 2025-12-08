package com.example.nto.service.impl;

import com.example.nto.dto.EmployeeInfoDto;
import com.example.nto.dto.converter.EmployeeInfoConverter;
import com.example.nto.entity.Employee;
import com.example.nto.exception.NoEmployeeFoundException;
import com.example.nto.repository.EmployeeRepository;
import com.example.nto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeInfoDto getEmployeeInfoByCode(String authCode) {
        Employee employee = employeeRepository.findEmployeeByCode(authCode).orElseThrow(() -> new NoEmployeeFoundException("Сотрудника с таким кодом не существует"));
        return EmployeeInfoConverter.toDto(employee);
    }

    @Override
    public Optional<Employee> getEmployeeByCode(String authCode) {
        return employeeRepository.findEmployeeByCode(authCode);
    }

    @Override
    public boolean isCodeValid(String authCode) {
        return employeeRepository.findEmployeeByCode(authCode).isPresent();
    }
}
