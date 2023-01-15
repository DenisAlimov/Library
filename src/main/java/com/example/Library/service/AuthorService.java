package com.example.Library.service;

import com.example.Library.data.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<Author> getAuthors(int page, int size);

    Author createAuthor(Author author);

}
