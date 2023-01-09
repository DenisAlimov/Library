package com.example.Library.service;


import com.example.Library.Exception.NotFoundException;
import com.example.Library.data.Book;
import com.example.Library.data.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        Book book = null;
        try {
            book = bookRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException("Книги с id=" + id + " нет в библиотеке");
        }
        return book;
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }


}
