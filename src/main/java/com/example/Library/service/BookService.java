package com.example.Library.service;

import com.example.Library.request.BookAuthorRequest;
import com.example.Library.response.BookPostResponse;
import com.example.Library.response.BookResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface BookService {

    List<BookResponse> getAllBooks(int page, int size);

    BookPostResponse createBook(@RequestBody BookAuthorRequest bookAuthorRequest);

    BookResponse getBookById(int id);

}
