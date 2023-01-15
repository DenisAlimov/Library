package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookAuthorService {

    int createBookAuthor(Book book, Author author);

    void createBookAuthors(Book book, List<Author> requestAuthors);
}
