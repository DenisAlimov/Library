package com.example.Library.service;


import com.example.Library.data.Book;
import com.example.Library.data.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;

    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }


}
