package com.example.Library.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResponse implements Comparable<AuthorResponse> {
    private int authorId;
    private String authorFullName;
    private int booksQty;

    @Override
    public int compareTo(AuthorResponse o) {
        return this.authorFullName.compareTo(o.authorFullName);
    }
}
