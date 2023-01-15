package com.example.Library.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

//    List<Book> findAllWithOrderByBookName();
    //TODO могу тут добавить метод, который будет возвращать книги в требуемом порядке

    Page<Book> findAllByOrderByBookName(Pageable page);

    Book findBookByBookName(String name);
}
