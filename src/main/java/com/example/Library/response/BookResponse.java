package com.example.Library.response;

import com.example.Library.data.Author;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookResponse {
    private int bookId;
    private String bookName;
    private List<Author> authors;

}
