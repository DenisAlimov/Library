package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.AuthorRepository;
import com.example.Library.data.BookAuthor;
import com.example.Library.response.AuthorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorResponse> getAuthors(int page, int size) {
        Pageable nextPage = PageRequest.of(page, size);
        Page<Author> pagedBooks = authorRepository.findAllByOrderByAuthorFullName(nextPage);
        return pagedBooks.toList().stream()
                .map(author -> AuthorResponse.builder()
                        .authorId(author.getId())
                        .authorFullName(author.getAuthorFullName())
                        .booksQty(author.getBookAuthor().size())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void createAuthors(List<Author> authors, List<BookAuthor> bookAuthors) {
        authors.stream()
                .filter(Objects::nonNull)
                .filter(author -> !authorRepository.existsByAuthorFullName(author.getAuthorFullName()))
                .map(author -> new Author(author.getId(), author.getAuthorFullName(), bookAuthors))
                .map(this::createAuthor)
                .collect(Collectors.toList());
    }
}
