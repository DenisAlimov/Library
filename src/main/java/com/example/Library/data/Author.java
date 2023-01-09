package com.example.Library.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @JsonProperty("authorId")
    private int id;

    @JsonProperty("authorFullName")
    private String authorFullName;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<BookAuthor> bookAuthor = new HashSet<>();

}
