package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.Book;
import com.example.Library.data.BookAuthor;
import com.example.Library.data.BookAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public int createBookAuthor(Book book, Author author) {
        BookAuthor bookAuthor = new BookAuthor(author, book);
        System.out.println(book + " " + author + " " + bookAuthor);
        BookAuthor createdBookAuthor = bookAuthorRepository.save(bookAuthor);
        System.out.println(createdBookAuthor);
        return createdBookAuthor.getId();
    }

    @Override
    public void createBookAuthors(Book book, List<Author> requestAuthors) {
        requestAuthors.stream()
                .filter(Objects::nonNull)
                .forEach(author -> createBookAuthor(book, author));
    }

}
