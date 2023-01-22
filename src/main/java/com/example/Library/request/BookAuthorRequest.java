package com.example.Library.request;

import com.example.Library.data.Author;
import lombok.Data;

import java.util.List;

@Data
public class BookAuthorRequest {
    private String bookName;
    private List<Author> authors;
}
