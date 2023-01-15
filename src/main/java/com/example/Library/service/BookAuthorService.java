package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookAuthorService {

    List<Author> getAuthorsByBookId(int id);

    int createBookAuthor(Book book, Author author);
}
