package com.example.Library.controllers;

import com.example.Library.data.Wiki;
import com.example.Library.facade.BookDataFacade;
import com.example.Library.request.BookAuthorRequest;
import com.example.Library.response.AuthorResponse;
import com.example.Library.response.BookPostResponse;
import com.example.Library.response.BookResponse;
import com.example.Library.service.WikiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "books data")
public class BookController {
    private final BookDataFacade bookDataFacade;
    private final WikiService wikiService;

    public BookController(BookDataFacade bookDataFacade, WikiService wikiService) {
        this.bookDataFacade = bookDataFacade;
        this.wikiService = wikiService;
    }

    @PostMapping("/book")
    public BookPostResponse createBookWithAuthors(@RequestBody BookAuthorRequest bookAuthorRequest) {
        return bookDataFacade.createBookWithAuthors(bookAuthorRequest);
    }

    @GetMapping("/books")
    @ApiOperation("method to get map of books")
    public List<BookResponse> getAllBooks(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return bookDataFacade.getBooksWithAuthors(page, size);
    }

    @ApiOperation("method to get book by id")
    @GetMapping("/books/{id}")
    public BookResponse getBookById(@PathVariable int id) {
        return bookDataFacade.getBookById(id);
    }

    @ApiOperation("method to get list of authors")
    @GetMapping("/authors")
    public List<AuthorResponse> getAllAuthors(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return bookDataFacade.getAuthors(page, size);
    }

    @ApiOperation("method to get book information from wiki by id")
    @GetMapping("/books/{id}/wiki")
    public ResponseEntity<Wiki> getWikiInfo(@PathVariable int id) throws JsonProcessingException {
        return ResponseEntity.ok(wikiService.getWiki(id));
    }
}
