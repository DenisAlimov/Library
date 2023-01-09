package com.example.Library.response;

import com.example.Library.data.Author;
import com.example.Library.data.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class BookAuthorResponse {
    private int bookId;
    private String bookName;
//    @JsonIgnore
    private Map<Book, List<Author>> bookMap;
    private List<Author> authors;
}
