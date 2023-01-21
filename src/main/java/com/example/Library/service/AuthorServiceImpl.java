package com.example.Library.service;

import com.example.Library.data.Author;
import com.example.Library.data.AuthorRepository;
import com.example.Library.response.AuthorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Author> createAuthors(List<Author> authors) {
        return authorRepository.saveAll(authors);
    }

}
