package com.thentrees.bookservice.query.projection;

import com.thentrees.bookservice.command.data.Book;
import com.thentrees.bookservice.command.data.BookRepository;
import com.thentrees.bookservice.query.model.BookResponseModel;
import com.thentrees.bookservice.query.queries.GetAllBookQuery;
import com.thentrees.bookservice.query.queries.GetBookDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookProjection {

    private final BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> hadnle(GetAllBookQuery query) {
        List<Book> list = bookRepository.findAll();
        return list.stream().map(book -> {
            BookResponseModel bookResponseModel = new BookResponseModel();
            BeanUtils.copyProperties(book, bookResponseModel);
            return bookResponseModel;
        }).toList();
    }

    @QueryHandler
    public BookResponseModel handle(GetBookDetailQuery query) {
        BookResponseModel bookResponseModel = new BookResponseModel();
        bookRepository.findById(query.getId()).ifPresent(book -> {
           BeanUtils.copyProperties(book, bookResponseModel);
        });
        return bookResponseModel;
    }



}
