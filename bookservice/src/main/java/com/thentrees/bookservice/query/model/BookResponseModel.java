package com.thentrees.bookservice.query.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseModel {
    private String id;

    private String name;

    private String author;

    private boolean isReady;
}
