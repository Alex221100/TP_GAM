package com.repositories;

import com.entity.Patient;
import com.repositories.connections.PostgreSQLJDBC;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                            rs.getString("ADR1"),
                            rs.getString("ADR2"),
                            rs.getString("CP"),
                            rs.getString("VILLE"),
                            rs.getString("PAYS"),
                            rs.getString("TEL"),
                            rs.getString("PAYSN"),
                            rs.getString("DDS")
                    );
                }
            }
        }
        catch(Exception e) {
            System.err.println("Impossible de récupérer le patient : " + e.getMessage());
        }
        return patient;
    }

    public int updatePatient(Patient patient){
        try {
            Connection connection = PostgreSQLJDBC.getConnection();
            Statement statement = connection.createStatement();
            String sql = "UPDATE Patient SET DATEMSG = '"+ patient.getDATEMSG() + "',NUMMSG = '"+ patient.getNUMMSG()+
                    "',NOM = '" + patient.getNOM() + "',PRENOM = '" + patient.getPRENOM() + "',INTIT = '" +
                    patient.getINTIT() + "',NOMMAR = '" + patient.getNOMMAR() + "',DDN = '" + patient.getDDN() +
                    "',SEXE = '" + patient.getSEXE() + "',ADR1 = '" + patient.getADR1() + "',ADR2 = '" +
                    patient.getADR2() + "',CP = '" + patient.getCP() + "',VILLE = '" + patient.getVILLE() +
                    "',PAYS = '" + patient.getPAYS() + "',TEL = '" + patient.getTEL() + "',PAYSN = '" +
                    patient.getPAYSN() + "',DDS = '" + patient.getDDS() + "' WHERE IPP = '" + patient.getIPP() + "';";

            statement.executeUpdate(sql);
            statement.close();
            connection.close();
            return statement.getUpdateCount();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
