package com.example.Library.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findAllByOrderByBookName(Pageable page);

    Book findBookByBookName(String name);
}
