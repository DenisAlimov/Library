DROP TABLE IF EXISTS book_author;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors
(
    id               INTEGER      NOT NULL,
    author_full_name VARCHAR(250) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE books
(
    id        SERIAL PRIMARY KEY,
    book_name VARCHAR(250) NOT NULL
);


CREATE TABLE book_author
(
    id        SERIAL PRIMARY KEY,
    book_id   INT NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id),
    FOREIGN KEY (author_id) REFERENCES authors (id)
);

insert into books (id, book_name)
values (3434, 'Мастер и Маргарита');
insert into books (id, book_name)
values (4555, 'Двенадцать стульев');


insert into authors (id, author_full_name)
values (1111, 'Михаил Афанасьевич Булгаков');
insert into authors (id, author_full_name)
values (111, 'Илья Арнольдович Ильф');
insert into authors (id, author_full_name)
values (222, 'Евгений Петрович Петров');


insert into book_author (id, book_id, author_id)
values (1000, 3434, 1111);
insert into book_author (id, book_id, author_id)
values (1001, 4555, 111);
insert into book_author (id, book_id, author_id)
values (1002, 4555, 222);

