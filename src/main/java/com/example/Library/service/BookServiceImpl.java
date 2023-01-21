package com.example.Library.service;


import com.example.Library.Exception.NotFoundException;
import com.example.Library.Exception.ValidationException;
import com.example.Library.data.Author;
import com.example.Library.data.Book;
import com.example.Library.data.BookAuthor;
import com.example.Library.data.BookRepository;
import com.example.Library.request.BookAuthorRequest;
import com.example.Library.response.BookPostResponse;
import com.example.Library.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorService authorService;

    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorService authorService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookResponse> getAllBooks(int page, int size) {
        Pageable nextPage = PageRequest.of(page, size);
        Page<Book> pagedBooks = bookRepository.findAllByOrderByBookName(nextPage);

        return pagedBooks.toList().stream()
                .map(book -> BookResponse.builder()
                        .bookId(book.getId())
                        .bookName(book.getBookName())
                        .authors(book.getBookAuthor().stream()
                                .map(BookAuthor::getAuthor)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

//    @Transactional
    public BookPostResponse createBook(@RequestBody BookAuthorRequest bookAuthorRequest) {
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

        List<Author> requestAuthors = bookAuthorRequest.getAuthors();

        authorService.createAuthors(requestAuthors);

        Book createdBook = bookRepository.save(new Book(bookAuthorRequest.getBookName(), requestAuthors));

        return BookPostResponse.builder()
                .id(createdBook.getId())
                .build();
    }

    public BookResponse getBookById(int id) {
        Book book = null;
        try {
            book = bookRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException("Книги с id=" + id + " нет в библиотеке");
        }
        return BookResponse.builder()
                .bookId(book.getId())
                .bookName(book.getBookName())
                .authors(book.getBookAuthor().stream()
                        .map(BookAuthor::getAuthor)
                        .collect(Collectors.toList()))
                .build();
    }
}
