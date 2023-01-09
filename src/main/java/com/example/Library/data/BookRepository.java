package com.example.Library.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

//    List<Book> findAllWithOrderByBookName();
    //TODO могу тут добавить метод, который будет возвращать книги в требуемом порядке
}
