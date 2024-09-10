package com.thentrees.bookservice.command.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDeleteEvent {
    private String id;
}
