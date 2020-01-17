package com.home;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.home.generated.Tables.BOOKS;
import static com.home.generated.Tables.BOOK_AUTHOR;

/**
 * Created by nani71 on 17/01/2020
 */
@SuppressWarnings("Duplicates")
public class GroupCountTest {

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
            Result<?> booksWithAuthorsNum = dsl
                    .select(BOOKS.TITLE, DSL.count(BOOK_AUTHOR.AUTHOR_ID))
                    .from(BOOKS)
                    .leftJoin(BOOK_AUTHOR)
                    .on(BOOK_AUTHOR.BOOK_ID.eq(BOOKS.ID))
                    .groupBy(BOOK_AUTHOR.BOOK_ID)
                    .fetch();

            System.out.println(booksWithAuthorsNum);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
