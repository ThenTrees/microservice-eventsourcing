package com.thentrees.employeeservice.command.event;

import com.thentrees.employeeservice.command.data.Employee;
import com.thentrees.employeeservice.command.data.EmployeeRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeEventHandler {

    private final EmployeeRepository employeeRepository;
    @EventHandler
    public void on(EmployeeCreateEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(EmployeeUpdateEvent event) {
        Optional<Employee> oldEmployee = employeeRepository.findById(event.getId());
        if(oldEmployee.isEmpty()) {
            throw new NotFoundException("Employee not found");
        }
        oldEmployee.ifPresent(employee -> {
            BeanUtils.copyProperties(event, employee);
            employeeRepository.save(employee);
        });
    }

    @EventHandler
    public void on(EmployeeDeleteEvent event) {
        Optional<Employee> oldEmployee = employeeRepository.findById(event.getId());
        if(oldEmployee.isEmpty()) {
            throw new NotFoundException("Employee not found");
        }
        employeeRepository.delete(oldEmployee.get());
    }

}
