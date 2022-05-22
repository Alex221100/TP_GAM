package com.repositories;

import com.entity.Entry;
import com.entity.Patient;
import com.repositories.connections.PostgreSQLJDBC;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.logging.Logger;

@Repository
public class PostgreRepository implements IPostgreRepository{

    static Logger logger = Logger.getLogger("Gam_Logger");

    public Patient getPatientByIpp(String patientIpp) {
        Patient patient = null;
        String sql = "SELECT * FROM Patient WHERE IPP= '" + patientIpp + "';";

        logger.info("Begin getPatientByIpp...");

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
            logger.info("Error during the recover of the patient");
            e.printStackTrace();
        }
        return patient;
    }

    public Entry getEntryByIpp(String patientIpp){
        Entry entry = null;
        String sql = "SELECT * FROM Entry WHERE IPP= '" + patientIpp + "' AND DATE_SORTIE is NULL;";
        logger.info("Begin getEntryByIpp...");
        try {
            Connection connection = PostgreSQLJDBC.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs != null) {
                if (rs.next()) {
                    entry = new Entry(
                            rs.getString("IPP"),
                            rs.getString("IEP"),
                            rs.getString("DATE_ENTREE"),
                            rs.getString("DATE_SORTIE"),
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
            logger.info("Error during the recover of the entry");
            e.printStackTrace();
        }
        return entry;
    }

    public int updatePatient(Patient patient){
        logger.info("Begin updatePatient...");
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
            int updatedCount = statement.getUpdateCount();
            statement.close();
            connection.close();
            return updatedCount;
        } catch (SQLException e) {
            logger.info("Error during the updated of the patient");
            e.printStackTrace();
        }
        return 0;
    }

    public int updateEntry(Entry entry){
        logger.info("Begin updateEntr...");
        try {
            Connection connection = PostgreSQLJDBC.getConnection();
            Statement statement = connection.createStatement();
            String sql = "UPDATE Entry SET IEP = '" + entry.getIEP() +
            "',DATE_ENTREE = '" + entry.getDATE_ENTREE() + "',DATE_SORTIE = '" + entry.getDATE_SORTIE() +
            "',UF = '" + entry.getUF() + "',CHAMBRE = '" + entry.getCHAMBRE() + "',LIT = '" + entry.getLIT() +
            "',UFMED = '" + entry.getUFMED() + "',NUMPAS = '" + entry.getNUMPAS() + "' WHERE IPP = '" +
                    entry.getIPP() + "';";

            int updatedCount = statement.getUpdateCount();

            statement.close();
            connection.close();
            return updatedCount;
        } catch (SQLException e) {
            logger.info("Error during the updated of the entry");
            e.printStackTrace();
        }
        return 0;
    }

}
