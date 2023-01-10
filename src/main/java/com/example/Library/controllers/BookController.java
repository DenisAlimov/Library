package com.example.Library.controllers;

import com.example.Library.facade.BookDataFacade;
import com.example.Library.request.BookAuthorRequest;
import com.example.Library.response.AuthorResponse;
import com.example.Library.response.BookPostResponse;
import com.example.Library.response.BookResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "books data")
public class BookController {
    private final BookDataFacade bookDataFacade;

    public BookController(BookDataFacade bookDataFacade) {
        this.bookDataFacade = bookDataFacade;
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @PostMapping("/book")
    public BookPostResponse createBookWithAuthors(@RequestBody BookAuthorRequest bookAuthorRequest) {
        return bookDataFacade.createBookWithAuthors(bookAuthorRequest);
    }

    @GetMapping("/books")
    @ApiOperation("method to get map of books")
    public List<BookResponse> getAllBooks() {
        return bookDataFacade.getBooksWithAuthors();
    }

    @ApiOperation("method to get book by id")
    @GetMapping("/books/{id}")
    public BookResponse getBookById(@PathVariable int id) {
        return bookDataFacade.getBookById(id);
    }

    @ApiOperation("method to get list of authors")
    @GetMapping("/authors")
    public List<AuthorResponse> getAllAuthors() {
        return bookDataFacade.getAuthors();
    }

}
