package com.example.nto.controller;
import com.example.nto.dto.EmployeeInfoDto;
import com.example.nto.dto.converter.EmployeeInfoConverter;
import com.example.nto.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    @GetMapping("{code}/auth")
    public ResponseEntity<String> auth(@PathVariable("code") String code) {
        if (code == null || code.isEmpty()) {
            return new ResponseEntity<>("пустой код", HttpStatus.BAD_REQUEST);
        }

        if (employeeService.isCodeValid(code)) {
            return new ResponseEntity<>("гуд", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("пользователя такого нет", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("{code}/info")
    public ResponseEntity<?> info(@PathVariable("code") String code) {
        System.out.println("1111111111111111111111111111111111111111" + " " + code);

        if (code == null || code.isEmpty()) {
            return new ResponseEntity<>("пустой код", HttpStatus.BAD_REQUEST);
        }

        Optional<EmployeeInfoDto> einfoOpt = employeeService.getEmployeeInfoByCode(code);
        if (einfoOpt.isEmpty()) {
            return new ResponseEntity<>("Такой пользователь не найден", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(einfoOpt.get(), HttpStatus.OK);
    }
}
