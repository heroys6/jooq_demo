package com.home;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.home.generated.Tables.AUTHORS;

/**
 * Created by nani71 on 11/01/2020
 */
@SuppressWarnings("Duplicates")
public class SelectTest {

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

            // Get all authors
            Result<?> authors = dsl
                    .select(AUTHORS.FULL_NAME, AUTHORS.BIRTHDAY)
                    .from(AUTHORS)
                    .fetch();

            System.out.println(authors);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
