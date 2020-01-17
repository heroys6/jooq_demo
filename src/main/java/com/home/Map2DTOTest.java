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
import java.util.List;

import static com.home.generated.Tables.BOOKS;

/**
 * Created by nani71 on 12/01/2020
 */
@SuppressWarnings("Duplicates")
public class Map2DTOTest {

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

            // Get book data by id and map it to DTO
            Long bookId = 2L;
            List<BookDTO> books = dsl
                    .selectFrom(BOOKS)
                    .where(BOOKS.ID.eq(bookId))
                    .fetchInto(BookDTO.class);

            books.forEach(System.out::println);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    @ToString
    private static class BookDTO {

        private Long id;
        private String title;
        private String genre;
        private LocalDateTime publishing_date;
    }
}
