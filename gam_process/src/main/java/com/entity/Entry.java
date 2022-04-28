package com.entity;

public class Entry {
    private String IPP;//identifiant permanent du patient
    private String IEP;//identifiant d'entrée du patient
    private String DATE_ENTREE;
    private String DATE_SORTIE;
    private String UF;//Unité fonctionnelle
    private String CHAMBRE;
    private String LIT;
    private String UFMED;//Ud méciacal
    private String NUMPAS;//numéro de passage

    public String getIPP() {
        return IPP;
    }

    public void setIPP(String IPP) {
        this.IPP = IPP;
    }

    public String getIEP() {
        return IEP;
    }

    public void setIEP(String IEP) {
        this.IEP = IEP;
    }

    public String getDATE_ENTREE() {
        return DATE_ENTREE;
    }

    public void setDATE_ENTREE(String DATE_ENTREE) {
        this.DATE_ENTREE = DATE_ENTREE;
    }

    public String getDATE_SORTIE() {
        return DATE_SORTIE;
    }

    public void setDATE_SORTIE(String DATE_SORTIE) {
        this.DATE_SORTIE = DATE_SORTIE;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCHAMBRE() {
        return CHAMBRE;
    }

    public void setCHAMBRE(String CHAMBRE) {
        this.CHAMBRE = CHAMBRE;
    }

    public String getLIT() {
        return LIT;
    }

    public void setLIT(String LIT) {
        this.LIT = LIT;
    }

    public String getUFMED() {
        return UFMED;
    }

    public void setUFMED(String UFMED) {
        this.UFMED = UFMED;
    }

    public String getNUMPAS() {
        return NUMPAS;
    }

    public void setNUMPAS(String NUMPAS) {
        this.NUMPAS = NUMPAS;
    }
}
