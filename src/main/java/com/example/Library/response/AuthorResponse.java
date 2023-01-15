package com.example.Library.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResponse {
    private int authorId;
    private String authorFullName;
    private int booksQty;

}
