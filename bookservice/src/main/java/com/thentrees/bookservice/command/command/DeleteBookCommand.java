package com.thentrees.bookservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String id;
}
