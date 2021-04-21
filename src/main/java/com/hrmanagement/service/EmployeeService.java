package com.hrmanagement.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hrmanagement.entities.EmployeeEntity;

@Service
public class EmployeeService {

    @Autowired
    private APICaller apiCall;

    public List<EmployeeEntity> getEmployees() {
        List<EmployeeEntity> list = null;
        try {
            list = apiCall.getEmployees();
        } catch (JsonMappingException e1) {
            e1.printStackTrace();
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return list;
    }

}
