package com.example.nto.controller;

import com.example.nto.exception.EmptyCodeException;
import com.example.nto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    @GetMapping("{code}/auth")
    public ResponseEntity<String> auth(@PathVariable("code") String code) {
        if (code == null || code.isEmpty()) {
            throw new EmptyCodeException("Был передан пустой код сотрудника");
        }

        if (employeeService.isCodeValid(code)) {
            return ResponseEntity.status(HttpStatus.OK).body("");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
    }

    @GetMapping("{code}/info")
    public ResponseEntity<?> info(@PathVariable("code") String code) {
        if (code == null || code.isEmpty()) {
            throw new EmptyCodeException("Был передан пустой код сотрудника");
        }

        return ResponseEntity.ok(employeeService.getEmployeeInfoByCode(code));
    }
}
