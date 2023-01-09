package com.example.Library.controllers;

import com.example.Library.data.BookAuthor;
import com.example.Library.data.BookAuthorRepository;
import com.example.Library.facade.BookDataFacade;
import com.example.Library.request.BookAuthorRequest;
import com.example.Library.response.AuthorResponse;
import com.example.Library.response.BookResponse;
import com.example.Library.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "books data")
public class BookController {

    private final BookDataFacade bookDataFacade;
    private final BookService bookService;
    private final BookAuthorRepository bookAuthorRepository;

    public BookController(BookDataFacade bookDataFacade, BookService bookService, BookAuthorRepository bookAuthorRepository) {
        this.bookDataFacade = bookDataFacade;
        this.bookService = bookService;

        this.bookAuthorRepository = bookAuthorRepository;
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @PostMapping("/book")
    public int createBookWithAuthors(@RequestBody BookAuthorRequest bookAuthorRequest) {
        return bookDataFacade.createBookWithAuthors(bookAuthorRequest);
    }

    @GetMapping("/books")
    public List<BookResponse> getAllBooks() {
        return bookDataFacade.getBooksWithAuthors();
    }

    @GetMapping("/books/{id}")
    public BookResponse getBookById(@PathVariable int id) {
        return bookDataFacade.getBookById(id);
    }

    @GetMapping("/authors")
    public List<AuthorResponse> getAllAuthors() {
        return bookDataFacade.getAuthors();
    }

    @GetMapping("/bookAuthors")
    public List<BookAuthor> getBookAuthors() {
        return bookAuthorRepository.findAll();
    }

//    @RequestMapping("/books/{id}/wiki")
//    public ResponseEntity<Object> getBookWiki(@PathVariable int id) {
//        return ResponseEntity.ok((bookService.getBookById(id).getBookName()));
//    }

}
