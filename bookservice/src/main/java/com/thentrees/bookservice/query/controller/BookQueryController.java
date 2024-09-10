package com.thentrees.bookservice.query.controller;

import com.thentrees.bookservice.query.model.BookResponseModel;
import com.thentrees.bookservice.query.queries.GetAllBookQuery;
import com.thentrees.bookservice.query.queries.GetBookDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/books")
@RequiredArgsConstructor
public class BookQueryController {

    private final QueryGateway queryGateway;

    @GetMapping
    public List<BookResponseModel> getAllBooks() {
        GetAllBookQuery query = new GetAllBookQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join(); //blocking thread
    }

    @GetMapping("/{bookId}")
    public BookResponseModel getBook(@PathVariable String bookId) {
        GetBookDetailQuery query = new GetBookDetailQuery(bookId);
        return queryGateway.query(query, ResponseTypes.instanceOf(BookResponseModel.class)).join();
    }
}
