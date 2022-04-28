package com.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Patient {
    private String DATEMSG;//now
    private String NUMMSG;//auto
    private String IPP;//identifiant permanent du patient
    private String NOM;//
    private String PRENOM;//
    private String INTIT;
    private String NOMMAR;//Nom marital
    private String DDN;//Date de naissance
    private String SEXE;//
    private String IEP;//
    private String ADR1;
    private String ADR2;
    private String CP;
    private String VILLE;
    private String PAYS;
    private String TEL;//
    private String PAYSN;
    private String DDS;//Démarche de soins
    private String UF;
    private String CHAMBRE;
    private String LIT;
    private String UFMED;
    private String NUMPAS;

    public Patient(String DATEMSG, String NUMMSG, String IPP, String NOM, String PRENOM, String INTIT, String NOMMAR, String DDN, String SEXE, String IEP, String ADR1, String ADR2, String CP, String VILLE, String PAYS, String TEL, String PAYSN, String DDS, String UF, String CHAMBRE, String LIT, String UFMED, String NUMPAS) {
        this.DATEMSG = DATEMSG;
        this.NUMMSG = NUMMSG;
        this.IPP = IPP;
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.INTIT = INTIT;
        this.NOMMAR = NOMMAR;
        this.DDN = DDN;
        this.SEXE = SEXE;
        this.IEP = IEP;
        this.ADR1 = ADR1;
        this.ADR2 = ADR2;
        this.CP = CP;
        this.VILLE = VILLE;
        this.PAYS = PAYS;
        this.TEL = TEL;
        this.PAYSN = PAYSN;
        this.DDS = DDS;
        this.UF = UF;
        this.CHAMBRE = CHAMBRE;
        this.LIT = LIT;
        this.UFMED = UFMED;
        this.NUMPAS = NUMPAS;
    }
}