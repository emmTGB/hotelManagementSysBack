package com.oxlxs.hotelmanagementsysback.service;

import com.oxlxs.hotelmanagementsysback.dto.request.EmployeeRegisterRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.LoginRequest;
import com.oxlxs.hotelmanagementsysback.dto.response.EmployeeResponse;
import com.oxlxs.hotelmanagementsysback.entity.Employee;
import com.oxlxs.hotelmanagementsysback.entity.EmployeeRole;
import com.oxlxs.hotelmanagementsysback.exception.employee.EmployeeAlreadyExistsException;
import com.oxlxs.hotelmanagementsysback.exception.employee.EmployeeNotExistsException;
import com.oxlxs.hotelmanagementsysback.exception.employee.EmployeePasswordWrongException;
import com.oxlxs.hotelmanagementsysback.repository.EmployeeDAO;
import com.oxlxs.hotelmanagementsysback.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeDAO employeeDAO;

    public Map<String, String> register(EmployeeRegisterRequest request) throws RuntimeException {
        if (employeeDAO.existsByUsername(request.getUsername())) {
            throw new EmployeeAlreadyExistsException();
        }
        Employee employee = new Employee();
        employee.setUsername(request.getUsername());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setRole(EmployeeRole.valueOf(request.getRole()));

        String accessToken = jwtProvider.createEmployeeToken(employee.getUsername(), employee.getRole());

        Long id = employeeDAO.save(employee).getId();

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", accessToken);
        map.put("id", String.valueOf(id));
        map.put("role", String.valueOf(employee.getRole()));
        return map;
    }

    public Map<String, String> login(LoginRequest request) throws RuntimeException {
        Employee employee = employeeDAO.findByUsername(request.getUsername()).orElseThrow(EmployeeNotExistsException::new);

        if(!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            throw new EmployeePasswordWrongException();
        }

        String accessToken = jwtProvider.createEmployeeToken(employee.getUsername(), employee.getRole());

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", accessToken);
        map.put("id", String.valueOf(employee.getId()));
        map.put("role", String.valueOf(employee.getRole()));
        return map;
    }

    public List<EmployeeResponse> list() throws RuntimeException{
        List<Employee> employees = employeeDAO.findAll();
        return employees.stream().map(EmployeeResponse::new).toList();
    }

    public void reset(Long id) throws RuntimeException{
        Employee employee = employeeDAO.findById(id)
                .orElseThrow(EmployeeNotExistsException::new);

        employee.setPassword(passwordEncoder.encode("123456"));
        employeeDAO.save(employee);
    }

    public void delete(Long id) throws RuntimeException{
        employeeDAO.deleteById(id);
    }
}
