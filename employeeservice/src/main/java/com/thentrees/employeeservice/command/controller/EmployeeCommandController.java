package com.thentrees.employeeservice.command.controller;


import com.thentrees.employeeservice.command.command.CreateEmployeeCommand;
import com.thentrees.employeeservice.command.command.DeleteEmployeeCommand;
import com.thentrees.employeeservice.command.command.UpdateEmployeeCommand;
import com.thentrees.employeeservice.command.model.CreateEmployeeModel;
import com.thentrees.employeeservice.command.model.UpdateEmployeeModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/employees")
@RequiredArgsConstructor
public class EmployeeCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@Valid @RequestBody CreateEmployeeModel model) {
        CreateEmployeeCommand command =
                new CreateEmployeeCommand(UUID.randomUUID().toString(),model.getFirstName(),model.getLastName(),model.getKin(),false);
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{employeeId}")
    public String updateEmployee(@PathVariable String employeeId, @Valid @RequestBody UpdateEmployeeModel model) {
        UpdateEmployeeCommand command = UpdateEmployeeCommand.builder()
                .id(employeeId)
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .kin(model.getKin())
                .isDisciplined(model.getIsDisciplined())
                .build();
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        return commandGateway.sendAndWait(command);
    }
}
