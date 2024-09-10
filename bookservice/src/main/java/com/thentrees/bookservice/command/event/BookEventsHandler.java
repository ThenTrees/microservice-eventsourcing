package com.thentrees.bookservice.command.event;

import com.thentrees.bookservice.command.data.Book;
import com.thentrees.bookservice.command.data.BookRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookEventsHandler {

    // tuong tac voi database
    private final BookRepository bookRepository;

    @EventHandler
    public void on(BookCreateEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdateEvent event) {
        Optional<Book> bookExists = bookRepository.findById(event.getId());
        bookExists.ifPresent(book -> {
            BeanUtils.copyProperties(event, book);
            bookRepository.save(book);
        });
    }

    @EventHandler
    public void on(BookDeleteEvent event) {
        Optional<Book> bookExists = bookRepository.findById(event.getId());
        bookExists.ifPresent(bookRepository::delete);
    }

}
