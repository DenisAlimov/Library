package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.response.AuthorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<AuthorResponse> getAuthors(int page, int size);

    List<Author> createAuthors(List<Author> authors);

}
