package com.example.Library.request;

import com.example.Library.data.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookAuthorRequest {
    @JsonIgnore
    private int id;
    private String bookName;
    private List<Author> authors;
}
