package com.repositories.connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Logger;

public class PostgreSQLJDBC {

    private static boolean hasCreatedTables = false;

    static Logger logger = Logger.getLogger("Gam_Logger");

    private PostgreSQLJDBC() {
    }

    public static Connection getConnection() {
        Connection result = null;

        logger.info("Connection to the database begin...");

        try {
            Class.forName("org.postgresql.Driver");
            URL url = new URL("localhost:8888/config/getDbURL");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            result = DriverManager.getConnection(content.length() > 0 ? content.toString() : "jdbc:postgresql://postgres:5432/postgres", "postgres", "postgres");

            con.disconnect();
            if (!hasCreatedTables) {
                createTables();
            }
        } catch (Exception e) {
            logger.info("Error during the connection to the database");
            e.printStackTrace();
        }

        logger.info("Connection to the database successful");

        return result;
    }

    private static void createTables() {
        hasCreatedTables = true;
        logger.info("Begin the creation of the tables");

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
                        DDN TIMESTAMP,
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
                    
                    CREATE TABLE IF NOT EXISTS Entry (
                        IPP VARCHAR(20),
                        IEP VARCHAR(20),
                        DATE_ENTREE TIMESTAMP,
                        DATE_SORTIE VARCHAR(20),
                        UF VARCHAR(20),
                        CHAMBRE VARCHAR(20),
                        LIT VARCHAR(20),
                        UFMED VARCHAR(20),
                        NUMPAS VARCHAR(20)
                    );
                    
                    DELETE FROM Patient;
                    
                    INSERT INTO Patient (IPP, NOM, PRENOM) VALUES ('455434243', 'Drz', 'Alex');
                    INSERT INTO Patient (IPP, NOM, PRENOM) VALUES ('455434245', 'Ribaut', 'Thomas');
                    """;
            statement.executeUpdate(sql);

            logger.info("Creation of the table in the database successful");

            statement.close();
            connection.close();
        } catch (Exception e) {
            logger.info("Error during the creation of the database");
            e.printStackTrace();
        }
    }
}
