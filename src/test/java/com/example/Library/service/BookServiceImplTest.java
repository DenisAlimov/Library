package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.AuthorRepository;
import com.example.Library.data.Book;
import com.example.Library.data.BookRepository;
import com.example.Library.request.BookAuthorRequest;
import com.example.Library.response.BookPostResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookServiceImpl;
    @Mock
    private AuthorServiceImpl authorServiceImpl;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void savedBookHasId() {
        // Arrange
        AuthorService authorService = new AuthorServiceImpl(authorRepository);

        int bookId = 1;
        String bookName = "Ангелы и Демоны";

        Author author = Author.builder()
                .id(1)
                .authorFullName("Дэн Браун")
                .build();
        Author author1 = Author.builder()
                .id(2)
                .authorFullName("Джоан Роулинг")
                .build();

        List<Author> authors = List.of(author, author1);

        BookAuthorRequest bookAuthorRequest = BookAuthorRequest.builder()
                .bookName(bookName)
                .authors(authors)
                .build();

        Book book = Book.builder()
                .id(bookId)
                .bookName(bookName)
                .build();

        when(authorRepository.saveAll(anyList())).thenReturn(authors);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        //Act
        List<Author> authorsAct = authorService.createAuthors(authors);
        BookPostResponse bookPostResponse = bookServiceImpl.createBook(bookAuthorRequest);

        //Assert
        assertThat(bookPostResponse.getId()).isEqualTo(bookId);
        verify(authorRepository, times(1)).saveAll(authors);
        verify(bookRepository, times(1)).save(any(Book.class));
//        verify(bookRepository, times(1)).save(eq(book));
    }
}