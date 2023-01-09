package com.example.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAuthorDto { //TODO по сути не использую, мб удалить?
    private int bookId;
    private int authorId;
}
