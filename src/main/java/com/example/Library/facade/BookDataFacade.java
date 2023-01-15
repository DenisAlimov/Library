package com.example.Library.facade;

import com.example.Library.Exception.ValidationException;
import com.example.Library.data.*;
import com.example.Library.request.BookAuthorRequest;
import com.example.Library.response.AuthorResponse;
import com.example.Library.response.BookPostResponse;
import com.example.Library.response.BookResponse;
import com.example.Library.service.AuthorService;
import com.example.Library.service.BookAuthorService;
import com.example.Library.service.BookService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BookDataFacade {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookAuthorService bookAuthorService;

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    public BookDataFacade(BookService bookService, AuthorService authorService, BookAuthorService bookAuthorService, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookAuthorService = bookAuthorService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookPostResponse createBookWithAuthors(@RequestBody BookAuthorRequest bookAuthorRequest) {

        if (bookAuthorRequest.getBookName() == null) {
            throw new ValidationException("Book title shouldn't be empty");
        }

        if (bookRepository.findBookByBookName(bookAuthorRequest.getBookName()) != null) {
            throw new ValidationException("You have already added this book");
        }

        if (bookAuthorRequest.getAuthors() == null || bookAuthorRequest.getAuthors().stream()
                .anyMatch(author -> author.getAuthorFullName() == null)) {
            throw new ValidationException("Book should have at least one author");
        }

        List<BookAuthor> bookAuthors = bookAuthorRequest
                .getAuthors()
                .stream()
                .map(author -> new BookAuthor(new Author(author.getId(), author.getAuthorFullName()), new Book(bookAuthorRequest.getBookName())))
                .collect(Collectors.toList());

        Book createdBook = bookService.createBook(new Book(0, bookAuthorRequest.getBookName(), bookAuthors));

        List<Author> createdAuthors = bookAuthorRequest.getAuthors()
                .stream()
                .filter(Objects::nonNull)
                .filter(author -> !authorRepository.existsByAuthorFullName(author.getAuthorFullName()))
                .map(author -> new Author(author.getId(), author.getAuthorFullName(), bookAuthors))
                .map(authorService::createAuthor)
                .collect(Collectors.toList());

        bookAuthorRequest.getAuthors().stream()
                .filter(Objects::nonNull)
                .forEach(author -> bookAuthorService.createBookAuthor(createdBook, author));

        return BookPostResponse.builder()
                .id(createdBook.getId())
                .build();
    }

    public List<BookResponse> getBooksWithAuthors(Integer page, Integer size) {
        return bookService.getAllBooks(page, size).stream()
                .map(book -> BookResponse.builder()
                        .bookId(book.getId())
                        .bookName(book.getBookName())
                        .authors(book.getBookAuthor().stream()
                                .map(BookAuthor::getAuthor)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(int id) {
        return BookResponse.builder()
                .bookId(id)
                .bookName(bookService.getBookById(id).getBookName())
                .authors(bookAuthorService.getAuthorsByBookId(id))
                .build();
    }

    public List<AuthorResponse> getAuthors(int page, int size) {
        List<Author> authors = authorService.getAuthors(page, size);
        return authors.stream()
                .map(author -> AuthorResponse.builder()
                        .authorId(author.getId())
                        .authorFullName(author.getAuthorFullName())
                        .booksQty(author.getBookAuthor().size())
                        .build())
                .collect(Collectors.toList());
    }
}
