package com.hrmanagement.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmanagement.entities.EmployeeEntity;

@Component
public class APICaller {

    @Autowired
    private RestTemplate restTemplate;

    final String GET_ALL_USERS_url = "http://localhost:8080/Management/employees";
    final String ADD_EMPLOYEE_url = "http://localhost:8080/Management/employee";

    public List<EmployeeEntity> getEmployees() throws JsonMappingException, JsonProcessingException {
        EmployeeEntity[] result = null;
        try {

            result = restTemplate.getForObject(GET_ALL_USERS_url, EmployeeEntity[].class);

        } catch (Exception e) {
        }
        List<EmployeeEntity> list = result != null ? Arrays.asList(result) : null;

        return list;

    }

    public void addEmployee(EmployeeEntity employeeEntity) {

        try {
            restTemplate.postForObject(ADD_EMPLOYEE_url, employeeEntity, EmployeeEntity.class);
        } catch (Exception e) {
        }

    }

    public void updateEmployee(EmployeeEntity emp, int employeeId) {
        final String uri = "http://localhost:8080/Management/employee/" + employeeId;
        try {
            restTemplate.put(uri, emp, EmployeeEntity.class);
        } catch (Exception e) {
        }
    }

    public String fetchAllEmployeesFile() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GET_ALL_USERS_url, String.class);
    }
}
