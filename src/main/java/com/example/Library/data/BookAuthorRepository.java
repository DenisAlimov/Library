package com.example.Library.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
    List<BookAuthor> findBookAuthorsByAuthor_Id(int id);

    List<BookAuthor> findBookAuthorsByBook_Id(int id);

}
