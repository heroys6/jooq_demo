package com.home;

import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.home.generated.Tables.BOOKS;

/**
 * Created by nani71 on 13/01/2020
 */
@SuppressWarnings("Duplicates")
public class InsertMultiple {

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

            List<BookDTO> books = List.of(
                    new BookDTO("The Lord of the Rings", "Novel", LocalDateTime.now()),
                    new BookDTO("Harry Potter", "Fantasy Fiction", LocalDateTime.now())
            );

            // Insert multiple books
            Integer insertedBooks = books
                    .foldLeft(
                            dsl.insertInto(BOOKS, BOOKS.TITLE, BOOKS.GENRE, BOOKS.PUBLISHING_DATE),
                            (query, book) -> query.values(
                                    book.getTitle(),
                                    book.getGenre(),
                                    Timestamp.valueOf(book.getPublishing_date())
                            )
                    )
                    .execute();

            System.out.println("Inserted books: " + insertedBooks);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    @ToString
    private static class BookDTO {

        private String title;
        private String genre;
        private LocalDateTime publishing_date;
    }
}
