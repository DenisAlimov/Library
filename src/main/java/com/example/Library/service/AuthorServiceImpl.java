package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }
}
