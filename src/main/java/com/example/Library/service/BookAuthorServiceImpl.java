package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.Book;
import com.example.Library.data.BookAuthor;
import com.example.Library.data.BookAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public BookAuthor getBookAuthor(int id) {
        return bookAuthorRepository.findById(id).get();
    }

    @Override
    public List<Author> getAuthorsByBookId(int id) {
        return bookAuthorRepository.findBookAuthorsByBook_Id(id)
                .stream()
                .map(BookAuthor::getAuthor)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByAuthorId(int id) {
        return bookAuthorRepository.findBookAuthorsByBook_Id(id)
                .stream()
                .map(BookAuthor::getBook)
                .collect(Collectors.toList());
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
    public int countBookQuantity(Author author) {
        return bookAuthorRepository.findBookAuthorsByAuthor_Id(author.getId()).size();
    }
}
