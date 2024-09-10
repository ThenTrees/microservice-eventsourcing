package com.thentrees.bookservice.command.controller;

import com.thentrees.bookservice.command.command.CreateBookCommand;
import com.thentrees.bookservice.command.command.DeleteBookCommand;
import com.thentrees.bookservice.command.command.UpdateBookCommand;
import com.thentrees.bookservice.command.model.BookRequestModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/books")
@RequiredArgsConstructor
public class BookCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<String> addBook(@Valid @RequestBody BookRequestModel bookRequestModel) {
        CreateBookCommand command = CreateBookCommand.builder()
                                                    .id(UUID.randomUUID().toString())
                                                    .name(bookRequestModel.getName())
                                                    .author(bookRequestModel.getAuthor())
                                                    .isReady(true)
                                                    .build();

        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("Book added : "+ command.getId());
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook(@RequestBody BookRequestModel bookRequestModel, @PathVariable String bookId) {
        UpdateBookCommand command = UpdateBookCommand.builder()
                                                    .id(bookId)
                                                    .name(bookRequestModel.getName())
                                                    .author(bookRequestModel.getAuthor())
                                                    .isReady(bookRequestModel.isReady())
                                                    .build();

        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("Book updated : "+ bookId);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        commandGateway.sendAndWait(command);
        return ResponseEntity.noContent().build();
    }

}
