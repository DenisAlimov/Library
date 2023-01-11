package com.example.Library.facade;

import com.example.Library.Exception.ValidationException;
import com.example.Library.data.Author;
import com.example.Library.data.Book;
import com.example.Library.dto.BookDto;
import com.example.Library.request.BookAuthorRequest;
import com.example.Library.response.AuthorResponse;
import com.example.Library.response.BookPostResponse;
import com.example.Library.response.BookResponse;
import com.example.Library.service.AuthorService;
import com.example.Library.service.BookAuthorService;
import com.example.Library.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BookDataFacade {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookAuthorService bookAuthorService;
    private final ModelMapper modelMapper;

    public BookDataFacade(BookService bookService, AuthorService authorService, BookAuthorService bookAuthorService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookAuthorService = bookAuthorService;
        this.modelMapper = modelMapper;
    }

    public BookPostResponse createBookWithAuthors(@RequestBody BookAuthorRequest bookAuthorRequest) {

        if (bookAuthorRequest.getBookName() == null) {
            throw new ValidationException("Book title shouldn't be empty");
        }

        if (bookAuthorRequest.getAuthors() == null || bookAuthorRequest.getAuthors().stream()
                .anyMatch(author -> author.getAuthorFullName() == null)) {
            throw new ValidationException("Book should have at least one author");
        }

        BookDto bookDto = new BookDto(bookAuthorRequest.getBookName());
        Book createdBook = bookService.createBook(modelMapper.map(bookDto, Book.class));

        List<Author> createdAuthors = bookAuthorRequest.getAuthors()
                .stream()
                .filter(Objects::nonNull)
                .map(authorDto -> authorService.createAuthor(modelMapper.map(authorDto, Author.class)))
                .collect(Collectors.toList());

        createdAuthors
                .forEach(author -> bookAuthorService.createBookAuthor(createdBook, author));

        return BookPostResponse.builder()
                .id(createdBook.getId())
                .build();
    }

    public List<BookResponse> getBooksWithAuthors() {
        return bookService.getAllBooks().stream()
                .map(book -> BookResponse.builder()
                        .bookId(book.getId())
                        .bookName(book.getBookName())
                        .authors(bookAuthorService.getAuthorsByBookId(book.getId()))
                        .build())
                .collect(Collectors.toList())
                .stream()
                .sorted().collect(Collectors.toList());
    }

    public BookResponse getBookById(int id) {
        return BookResponse.builder()
                .bookId(id)
                .bookName(bookService.getBookById(id).getBookName())
                .authors(bookAuthorService.getAuthorsByBookId(id))
                .build();
    }

    public List<AuthorResponse> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return authors.stream()
                .map(author -> AuthorResponse.builder()
                        .authorId(author.getId())
                        .authorFullName(author.getAuthorFullName())
                        .booksQty(bookAuthorService.countBookQuantity(author))
                        .build())
                .collect(Collectors.toList())
                .stream()
                .sorted().collect(Collectors.toList());
    }
}
