package com.example.Library.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bookName;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BookAuthor> bookAuthor = new ArrayList<>();

    public Book(String bookName) {
        this.bookName = bookName;
    }

    //    @ManyToMany
//    @JoinTable(name = "books_authors",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id")
//    )
//    private Set<Author> authorSet;
}
