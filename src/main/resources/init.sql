insert into books (id, book_name) values (3434, 'Мастер и Маргарита');
insert into books (id, book_name) values (4555, 'Двенадцать стульев');

insert into authors (id, author_full_name) values (1111, 'Михаил Афанасьевич Булгаков');
insert into authors (id, author_full_name) values (111, 'Илья Арнольдович Ильф'); --в примере json id 1111 дублируется с id Булгакова
insert into authors (id, author_full_name) values (222, 'Евгений Петрович Петров');

insert into book_author (id, book_id, author_id) values (1000, 3434, 1111);
insert into book_author (id, book_id, author_id) values (1001, 4555, 111);
insert into book_author (id, book_id, author_id) values (1002, 4555, 222);

