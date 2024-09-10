package com.thentrees.bookservice.command.event;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateEvent {

    private String id;

    private String name;

    private String author;

    private boolean isReady;

}
