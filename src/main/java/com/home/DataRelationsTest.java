package com.home;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.home.generated.Tables.*;

/**
 * Created by nani71 on 12/01/2020
 */
@SuppressWarnings("Duplicates")
public class DataRelationsTest {

    public static void main(String[] args) {
        // Parse program arguments
        String userName = args[0];
        String password = args[1];
        String host = args[2];
        String port = args[3];
        String schemaName = args[4];

        String url = "jdbc:mysql://"
                + host + ":"
                + port + "/"
                + schemaName + "?useUnicode=true&amp;characterEncoding=utf8";

        // Create connection
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext dsl = DSL.using(conn, SQLDialect.MYSQL);

            // Get books with authors by id
            List<Long> bookIds = Arrays.asList(2L, 3L);
            List<BookWithAuthorsDTO> books = dsl
                    .select(BOOKS.ID, BOOKS.TITLE, BOOKS.GENRE, BOOKS.PUBLISHING_DATE,
                            AUTHORS.FULL_NAME, AUTHORS.BIRTHDAY)
                    .from(BOOK_AUTHOR)
                    .leftJoin(BOOKS)
                    .on(BOOK_AUTHOR.BOOK_ID.eq(BOOKS.ID))
                    .leftJoin(AUTHORS)
                    .on(BOOK_AUTHOR.AUTHOR_ID.eq(AUTHORS.ID))
                    .where(BOOK_AUTHOR.BOOK_ID.in(bookIds))
                    .fetchGroups(BOOKS.ID)
                    .values()
                    .stream()
                    .map(groupedByBookId -> {
                        // Here we got joined books with authors:
                        // book1 author1
                        // book1 author2
                        System.out.println("Grouped rows:\n" + groupedByBookId);

                        // If we have grouped something - we have at least one row
                        // Map book data from row into DTO
                        BookWithAuthorsDTO bookWithoutAuthors = groupedByBookId
                                .get(0)
                                .into(BookWithAuthorsDTO.class);

                        // Map author data from rows into DTOs
                        List<AuthorDTO> authors = groupedByBookId
                                .stream()
                                .map(record -> record.into(AuthorDTO.class))
                                .collect(Collectors.toList());

                        bookWithoutAuthors.setAuthors(authors);

                        return bookWithoutAuthors;
                    })
                    .collect(Collectors.toList());

            books.forEach(x -> System.out.println("BookDTO:\n" + x));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    @ToString
    private static class AuthorDTO {

        private String full_name;
        private LocalDateTime birthday;
    }

    @Data
    @AllArgsConstructor
    private static class BookWithAuthorsDTO {

        private String title;
        private String genre;
        private LocalDateTime publishing_date;
        private List<AuthorDTO> authors;

        public String toString() {
            return "BookWithAuthorsDTO(\n"
                    + "\ttitle=" + this.getTitle() + ",\n"
                    + "\tgenre=" + this.getGenre() + ",\n"
                    + "\tpublishing_date=" + this.getPublishing_date() + ",\n"
                    + "\tauthors=" + this.getAuthors() + "\n"
                    + ")";
        }
    }
}
