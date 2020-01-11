# Many-To-Many
# One book has many authors, one author has many books

# Cleanup
drop table if exists book_author;
drop table if exists books;
drop table if exists authors;

# Create new tables
create table books
(
    id bigint auto_increment primary key,
    title varchar(255) not null,
    genre varchar(30),
    publishing_date datetime
);
create table authors
(
    id bigint auto_increment primary key,
    full_name varchar(50),
    birthday datetime
);
create table book_author
(
    book_id bigint not null,
    author_id bigint not null,
    primary key (book_id, author_id),
    constraint constr_bookAuthor_book_fk
        foreign key book_fk (book_id) references books (id)
        on delete cascade on update cascade,
    constraint constr_bookAuthor_author_fk
        foreign key author_fk (author_id) references authors (id)
        on delete cascade on update cascade
);

# Feel tables with test data
# Books
insert into books (id, title, genre, publishing_date)
VALUES (1, 'White Fang', 'Adventure', '1906-05-01 00:00:00'),
       (2, 'East of Eden', 'Novel', '1952-01-01 00:00:00'),
       (3, 'Tiny Pretty Things', 'Fiction', '2015-05-26 00:00:00');
# Authors
insert into authors (id, full_name, birthday)
VALUES (1, 'Jack London', '1876-01-12 00:00:00'),
       (2, 'John Steinbeck', '1902-11-27 00:00:00'),
       (3, 'Dhonielle Clayton', '1978-07-05 00:00:00'),
       (4, 'Sona Charaipotra', '1992-08-29 00:00:00');
# BookAuthors
insert into book_author (book_id, author_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (3, 4);