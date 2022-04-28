package com.repositories;

import com.entity.Patient;
import com.repositories.connections.PostgreSQLJDBC;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class PostgreRepository implements IPostgreRepository{
    public Patient getPatientByIpp(String patientIpp) {
        Patient patient = null;
        String sql = "SELECT * FROM Patient WHERE IPP= '" + patientIpp + "';";
        try {
            Connection connection = PostgreSQLJDBC.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                if (rs.next()) {
                    patient = new Patient(
                            rs.getString("DATEMSG"),
                            rs.getString("NUMMSG"),
                            rs.getString("IPP"),
                            rs.getString("NOM"),
                            rs.getString("PRENOM"),
                            rs.getString("INTIT"),
                            rs.getString("NOMMAR"),
                            rs.getString("DDN"),
                            rs.getString("SEXE"),
                            rs.getString("IEP"),
                            rs.getString("ADR1"),
                            rs.getString("ADR2"),
                            rs.getString("CP"),
                            rs.getString("VILLE"),
                            rs.getString("PAYS"),
                            rs.getString("TEL"),
                            rs.getString("PAYSN"),
                            rs.getString("DDS"),
                            rs.getString("UF"),
                            rs.getString("CHAMBRE"),
                            rs.getString("LIT"),
                            rs.getString("UFMED"),
                            rs.getString("NUMPAS")
                    );
                }
            }
        }
        catch(Exception e) {
            System.err.println("Impossible de récupérer le patient : " + e.getMessage());
        }
        return patient;
    }
}
