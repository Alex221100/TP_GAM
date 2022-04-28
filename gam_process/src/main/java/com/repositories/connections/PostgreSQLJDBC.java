package com.repositories.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQLJDBC {

    private static boolean hasCreatedTables = false;

    private PostgreSQLJDBC() {
    }

    public static Connection getConnection() {
        Connection result = null;

        try {
            Class.forName("org.postgresql.Driver");
            result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

            if (!hasCreatedTables) {
                createTables();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Opened database successfully");

        return result;
    }

    private static void createTables() {
        hasCreatedTables = true;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = """                                                   
                    CREATE TABLE IF NOT EXISTS Patient (
                        DATEMSG VARCHAR(20),
                        NUMMSG VARCHAR(20),
                        IPP VARCHAR(20),
                        NOM VARCHAR(20), 
                        PRENOM VARCHAR(20), 
                        INTIT VARCHAR(20), 
                        NOMMAR VARCHAR(20),
                        DDN VARCHAR(20),
                        SEXE VARCHAR(20),
                        ADR1 VARCHAR(200), 
                        ADR2 VARCHAR(200), 
                        CP VARCHAR(6), 
                        VILLE VARCHAR(20),
                        PAYS VARCHAR(20),
                        TEL VARCHAR(10),
                        PAYSN VARCHAR(20),
                        DDS VARCHAR(20)
                    );
                    
                    INSERT INTO Patient (IPP, NOM, PRENOM) VALUES ('455434243', 'Drz', 'Alex');
                    INSERT INTO Patient (IPP, NOM, PRENOM) VALUES ('455434245', 'Ribaut', 'Thomas');
                    """;
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Impossible de créer la table : " + e.getMessage());
        }
    }
}
