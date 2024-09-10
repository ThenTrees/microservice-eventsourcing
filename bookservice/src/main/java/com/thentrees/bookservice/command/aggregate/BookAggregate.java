package com.thentrees.bookservice.command.aggregate;

import com.thentrees.bookservice.command.command.CreateBookCommand;
import com.thentrees.bookservice.command.command.DeleteBookCommand;
import com.thentrees.bookservice.command.command.UpdateBookCommand;
import com.thentrees.bookservice.command.event.BookCreateEvent;
import com.thentrees.bookservice.command.event.BookDeleteEvent;
import com.thentrees.bookservice.command.event.BookUpdateEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor // require
public class BookAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand command) {
        BookCreateEvent bookCreateEvent = BookCreateEvent.builder().build();
        BeanUtils.copyProperties(command, bookCreateEvent);
        AggregateLifecycle.apply(bookCreateEvent); // publish event
    }

    @CommandHandler
    public void handle(UpdateBookCommand command) {
        BookUpdateEvent bookUpdateCommand = new BookUpdateEvent();
        BeanUtils.copyProperties(command, bookUpdateCommand);
        AggregateLifecycle.apply(bookUpdateCommand); // publish event
    }

    @CommandHandler
    public void handle(DeleteBookCommand command) {
        BookDeleteEvent bookDeleteEvent = new BookDeleteEvent();
        BeanUtils.copyProperties(command, bookDeleteEvent);
        AggregateLifecycle.apply(bookDeleteEvent); // publish event
    }

    @EventSourcingHandler
    public void on(BookCreateEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.isReady();
    }

    @EventSourcingHandler
    public void on(BookUpdateEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.isReady();
    }

    @EventSourcingHandler
    public void on(BookDeleteEvent event) {
        this.id = event.getId();
    }
}
