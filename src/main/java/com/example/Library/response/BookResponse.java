package com.example.Library.response;

import com.example.Library.data.Author;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookResponse implements Comparable<BookResponse>{
    private int bookId;
    private String bookName;
    private List<Author> authors;

    @Override
    public int compareTo(BookResponse o) {
        return o.bookName.compareTo(this.bookName);
//        return this.bookName.compareTo(o.bookName);
    }
}
