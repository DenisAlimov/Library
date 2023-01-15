package com.example.Library.data;

import io.swagger.annotations.ApiModel;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_author")
@ApiModel(description = "data model of bookAuthor entity")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookAuthor(Author author, Book book) {
        this.author = author;
        this.book = book;
    }
}
