package com.thentrees.employeeservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Learn Microservice with event sourcing",
                        email = "thientri.trank17@gmail.com"
                ),
                description = " APi documentation for employee service",
                title = "Learn Microservice with event sourcing",
                version = "1.0.0"
        ),
        servers ={ @Server(
         description = "local ENV",
         url = "http://localhost:9002/employee"
        ),
                @Server(
                        description = "Dev ENV",
                        url = "https://tnydevdocs.io.vn/employee"
                )
        }
)
public class OpenApiConfig {
}
