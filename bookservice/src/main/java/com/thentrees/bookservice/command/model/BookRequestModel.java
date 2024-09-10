package com.thentrees.bookservice.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestModel {
    private String id;

    @NotBlank(message = "name is require!")
    @Size(min = 2, max = 30, message = "name must be between 2 and 30 characters")
    private String name;
    @NotBlank(message = "author is require!")
    private String author;

    private boolean isReady;
}
