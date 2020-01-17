package com.home;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.home.generated.Tables.*;

/**
 * Created by nani71 on 12/01/2020
 */
@SuppressWarnings("Duplicates")
public class JoinTest {

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

            // Get authors by book id
            Long bookId = 3L;
            Result<?> authors = dsl
                    .select(BOOKS.TITLE, AUTHORS.FULL_NAME, AUTHORS.BIRTHDAY)
                    .from(BOOK_AUTHOR)
                    .leftJoin(AUTHORS)
                    .on(BOOK_AUTHOR.AUTHOR_ID.eq(AUTHORS.ID))
                    .leftJoin(BOOKS)
                    .on(BOOK_AUTHOR.BOOK_ID.eq(BOOKS.ID))
                    .where(BOOK_AUTHOR.BOOK_ID.eq(bookId))
                    .fetch();

            System.out.println(authors);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
