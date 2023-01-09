package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.Book;
import com.example.Library.data.BookAuthor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookAuthorService {


//    Map<Book, List<Author>> getAllBooks();
    BookAuthor getBookAuthor(int id);

    List<Author> getAuthorsByBookId(int id);

    List<Book> getBooksByAuthorId(int id);

    int createBookAuthor(Book book, Author author);

    int countBookQuantity(Author author);
}
