package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthors(int page, int size) {
        Pageable nextPage = PageRequest.of(page, size);
        Page<Author> pagedBooks = authorRepository.findAllByOrderByAuthorFullName(nextPage);
        return pagedBooks.toList();
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
}
