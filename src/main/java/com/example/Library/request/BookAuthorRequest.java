package com.example.Library.request;

import com.example.Library.data.Author;
import com.example.Library.data.BookAuthor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BookAuthorRequest {
    private String bookName;
    private List<Author> authors;
}
