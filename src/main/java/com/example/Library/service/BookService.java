package com.example.Library.service;

import com.example.Library.data.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> getAllBooks(int page, int size);

    Book createBook(Book book);

    Book getBookById(int id);

}
