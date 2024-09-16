package com.thentrees.employeeservice.query.projection;


import com.thentrees.employeeservice.command.data.Employee;
import com.thentrees.employeeservice.command.data.EmployeeRepository;
import com.thentrees.employeeservice.query.model.EmployeeResponseModel;
import com.thentrees.employeeservice.query.queries.GetAllEmployeeQuery;
import com.thentrees.employeeservice.query.queries.GetDetailEmployeeQuery;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeProjection {

    private static final Logger log = LoggerFactory.getLogger(EmployeeProjection.class);

    private final EmployeeRepository employeeRepository;

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query) {
        List<Employee> employees = employeeRepository.findAllByIsDisciplined(query.isDisciplined());
        return employees.stream().map(employee -> {
            EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee, employeeResponseModel);
            return employeeResponseModel;
        }
        ).toList();
    }

    @QueryHandler
    public EmployeeResponseModel handle(GetDetailEmployeeQuery query) throws NotFoundException {
            Employee employee = employeeRepository.findById(query.getId()).orElseThrow(() -> new NotFoundException("Employee not found"));
            EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee, employeeResponseModel);
            return employeeResponseModel;
    }
}


// day se xu ly voi db