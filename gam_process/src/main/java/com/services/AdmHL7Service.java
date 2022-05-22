package com.services;

import com.model.PatientEntry;
import com.services.interfaces.IAdmHL7Service;
import com.utils.IdGenerator;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Logger;

@Service
public class AdmHL7Service implements IAdmHL7Service {

    static Logger logger = Logger.getLogger("Gam_Logger");

    //format du fichier de sortie HL7 ORU vers la base de données.
    //ORU out
    //MSH10 Message control ID (R)
    String ADTMSH = "MSH|^~\\&|SA_AMCK|SF_MCK|DXSRA|admission|#DATEMSG#|A01|ADT^A01|#NUMMSG#|P|2.3.1||||||8859/1|";
    //EVN
    String ADTEVN="EVN|A01|#DATEMSG#||||";
    //PID 18 Patient control Account
    String ADTPID = "PID|1||#IPP#^^^IF_MCKN||#NOM#^#PRENOM#^^^#INTIT#^^L|#NOMMAR#|#DDN#|#SEXE#|||#ADR1#^#ADR2#^#VILLE#^^#CP#^#PAYS#|#PAYS#|#TEL#|||U||#IEP#||||||||||#PAYSN#^#PAYSN#||N";
    //PV1
    String ADTPV1 = "PV1|1|I|#UF#^#CHAMBRE#^#LIT#|R|||||||||||||||#IEP#||||||||||||||||||||||||| #DDS#||||||#NUMPAS#|A|";
    //PV2
    String ADTPV2="PV2|||^^^8^^|||||||||||||||||||N";
    //ZFU
    String ADTZFU="ZFU|#UF#|#DDS#|#UFMED#|#DDS#|#UF#|#DDS#";
    //ZRE
    String ADTZRE="ZRE|||||||||";

    /*****************************
     * VARIABLES GLOBALES
     ****************************/
    //identité patient et data patient
    private String DATEMSG;//now
    private String NUMMSG;//auto
    private String IPP;//
    private String NOM;//
    private String PRENOM;//
    private String INTIT;
    private String NOMMAR;//
    private String DDN;//
    private String SEXE;//
    private String ADR1;
    private String ADR2;
    private String CP;
    private String VILLE;
    private String PAYS;
    private String TEL;//
    private String PAYSN;
    private String DDS;
    private String NUMPAS;
    private String IEP;//a générer
    private String UF;//
    private String CHAMBRE;
    private String LIT;
    private String UFMED;

    //newlines
    final int CR = 13;
    final int LF = 10;

    /*************************************************************
     * initialisation d'un patient
     * @param patient
     *************************************************************/
    public void initPatient(PatientEntry patient) {
        logger.info("Begin the initialisation of the patient...");
        Calendar c=Calendar.getInstance();

        //Patient
        this.IPP= IdGenerator.generateId();
        this.NOM = patient.getNOM();
        this.PRENOM = patient.getPRENOM();
        this.INTIT= patient.getINTIT();
        this.NOMMAR = patient.getNOMMAR();
        this.DDN= patient.getDDN();
        this.SEXE = patient.getSEXE();
        this.ADR1= patient.getADR1();
        this.ADR2=patient.getADR2();
        this.CP=patient.getCP();
        this.VILLE=patient.getVILLE();
        this.PAYS=patient.getPAYS();
        this.TEL=patient.getTEL();
        this.PAYSN=patient.getPAYSN();

        NUMMSG=String.valueOf(System.currentTimeMillis());
        NUMPAS=String.valueOf(System.currentTimeMillis()-100);
        this.DDS=LocalDate.now()+" "+completeZero(c.get(Calendar.HOUR_OF_DAY))+":"+completeZero(c.get(Calendar.MINUTE));
        this.DATEMSG= LocalDate.now()+" "+completeZero(c.get(Calendar.HOUR_OF_DAY))+":"+completeZero(c.get(Calendar.MINUTE));

        //Entry
        this.IEP = IdGenerator.generateId();
        this.UF = patient.getUF();//
        this.CHAMBRE = patient.getCHAMBRE();
        this.LIT = patient.getLIT();
        this.UFMED = patient.getUFMED();

        logger.info("Initialisation of the patient successful");
    }

    private String completeZero(int in)
    {
        String out=""+in;
        if (out.length()<2){ out="0"+in;}
        return out;
    }

    /*********************************************************************
     * Fonction de remplacement des tag #XXXX# pour les segments HL7
     *
     * @param in (Chaine de caratere du segment a remplacer
     * @return String
     * @throws java.lang.Exception Erreur replaceAll
     *********************************************************************/
    private String hl7(String in) throws Exception {
        logger.info("Begin the replacement of the data in the hl7 format...");
        String outS = in;
        //Segment MSH
        outS = outS.replaceAll("#DATEMSG#", Date_to_Date_HL7_long(DATEMSG));
        outS = outS.replaceAll("#NUMMSG#", NUMMSG);
        outS = outS.replaceAll("#NUMPAS#", NUMPAS);

        //segment PID/PV1
        outS = outS.replaceAll("#INTIT#", INTIT);
        outS = outS.replaceAll("#NOM#", NOM);
        outS = outS.replaceAll("#NOMMAR#", NOMMAR);
        outS = outS.replaceAll("#PRENOM#", PRENOM);
        outS = outS.replaceAll("#SEXE#", SEXE);
        outS = outS.replaceAll("#DDS#",Date_to_Date_HL7_long(DDS));
        outS = outS.replaceAll("#DDN#", Date_to_Date_HL7_court(DDN));
        outS = outS.replaceAll("#ADR1#", ADR1);
        outS = outS.replaceAll("#ADR2#", ADR2);
        outS = outS.replaceAll("#CP#", CP);
        outS = outS.replaceAll("#VILLE#", VILLE);
        outS = outS.replaceAll("#PAYS#", PAYS);
        outS = outS.replaceAll("#PAYSN#", PAYSN);
        outS = outS.replaceAll("#IPP#", IPP);
        outS = outS.replaceAll("#IEP#", IEP);
        outS = outS.replaceAll("#UF#", UF);
        outS = outS.replaceAll("#CHAMBRE#", CHAMBRE);
        outS = outS.replaceAll("#LIT#", LIT);
        outS = outS.replaceAll("#TEL#",TEL);

        //segment ZFU
        outS = outS.replaceAll("#UFMED#", UFMED);

        logger.info("Replacement of the data in the hl7 format successful");
        return outS;
    }

    /*********************************************************************
     * convertir une date JJ/MM/AAAA HH:MM:SS en date hl7 AAAAMMJJHHMMSS
     *
     *
     * @param dateComplexe String
     * @return String
     ********************************************************************/
    private String Date_to_Date_HL7_long(String dateComplexe) {
        //parser la date
        String JJ = dateComplexe.substring(8, 10);
        String MM = dateComplexe.substring(5, 7);
        String AAAA = dateComplexe.substring(0, 4);
        String HH = dateComplexe.substring(11, 13);
        String mm = dateComplexe.substring(14, 16);
        return AAAA + MM + JJ + HH + mm + "00";
    }

    /*****************************************************
     * convertir une date JJ/MM/AAAA en date hl7 AAAAMMJJ
     *
     *
     * @param dateSimple String
     * @return String
     *****************************************************/
    private String Date_to_Date_HL7_court(String dateSimple) {
        //parser la date
        String JJ = dateSimple.substring(8, 10);
        String MM = dateSimple.substring(5, 7);
        String AAAA = dateSimple.substring(0, 4);
        return AAAA + MM + JJ + "000000";
    }

    /***************************************************
     * Procedure qui permet d'ecrire le fichier hl7_oru
     *
     *
     * @param outputFileName String
     * @return boolean
     * @throws java.lang.Exception Erreur d'ecriture sur disque
     ***************************************************/
    public boolean create_format(String outputFileName) throws Exception {
        logger.info("Begin to write in a file...");
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("out/" + outputFileName));
            out.write(hl7(ADTMSH));
            out.write(CR);
            out.write(hl7(ADTEVN));
            out.write(CR);
            out.write(hl7(ADTPID));
            out.write(CR);
            out.write(hl7(ADTPV1));
            out.write(CR);
            out.write(hl7(ADTPV2));
            out.write(CR);
            out.write(hl7(ADTZFU));
            out.write(CR);
            out.write(hl7(ADTZRE));
            out.flush();

            logger.info("Creation of the file successful");
        } catch (Exception e) {
            logger.info("Error during the writing in the file...");
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return true;
    } //fin create_adt

    /***************************************************
     * Procedure qui permet d'ecrire le fichier hl7_oru
     * @return boolean
     ***************************************************/
    public Boolean create_hl7(PatientEntry patient) throws Exception {
        logger.info("Begin the creation of the hl7 file...");

        //init patient
        initPatient(patient);

        //write in file
        return create_format("test_hl7");
    }

}
