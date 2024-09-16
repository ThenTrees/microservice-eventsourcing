package com.thentrees.employeeservice.query.controller;


import com.thentrees.employeeservice.query.model.EmployeeResponseModel;
import com.thentrees.employeeservice.query.queries.GetAllEmployeeQuery;
import com.thentrees.employeeservice.query.queries.GetDetailEmployeeQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/employees")
@RequiredArgsConstructor
@Tag(name = "Employee Query")
public class EmployeeQueryController {

    private final QueryGateway queryGateway;

    @Operation(
            summary = "Get list employee",
            description = "get endpoint for employee with filter",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorize / invalid token",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping
    public List<EmployeeResponseModel> getEmployees(@RequestParam Boolean isDisciplined) {
        return queryGateway.query(new GetAllEmployeeQuery(isDisciplined),
                ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getEmployeeById(@PathVariable String employeeId) {
        return queryGateway.query(new GetDetailEmployeeQuery(employeeId), ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
    }

}
