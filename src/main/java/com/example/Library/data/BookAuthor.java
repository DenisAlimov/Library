package com.example.Library.data;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "book_author")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @MapsId("author_id")
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @MapsId("book_id")
    @JoinColumn(name = "book_id")
    private Book book;

    public BookAuthor() {
    }

    public BookAuthor(Author author, Book book) {
        this.author = author;
        this.book = book;
    }
}
