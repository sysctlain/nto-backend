package com.example.nto.service;

import com.example.nto.dto.EmployeeInfoDto;
import com.example.nto.entity.Employee;

import java.util.Optional;


public interface EmployeeService {
    EmployeeInfoDto getEmployeeInfoByCode(String authCode);
    Optional<Employee> getEmployeeByCode(String authCode);

    boolean isCodeValid(String authCode);
}
