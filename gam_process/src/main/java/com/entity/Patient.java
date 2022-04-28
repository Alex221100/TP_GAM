package com.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Patient {
    private String DATEMSG;//now HL7
    private String NUMMSG;//auto HL7
    private String IPP;//identifiant permanent du patient
    private String NOM;
    private String PRENOM;
    private String INTIT;//Intitulé Monsieur, Madame, Mlle
    private String NOMMAR;//Nom marital
    private String DDN;//Date de naissance
    private String SEXE;
    private String ADR1;
    private String ADR2;
    private String CP;
    private String VILLE;
    private String PAYS;
    private String TEL;
    private String PAYSN;//Pays de naisssance
    private String DDS;//Démarche de soins

    public Patient(String DATEMSG, String NUMMSG, String IPP, String NOM, String PRENOM, String INTIT, String NOMMAR, String DDN, String SEXE, String ADR1, String ADR2, String CP, String VILLE, String PAYS, String TEL, String PAYSN, String DDS) {
        this.DATEMSG = DATEMSG;
        this.NUMMSG = NUMMSG;
        this.IPP = IPP;
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.INTIT = INTIT;
        this.NOMMAR = NOMMAR;
        this.DDN = DDN;
        this.SEXE = SEXE;
        this.ADR1 = ADR1;
        this.ADR2 = ADR2;
        this.CP = CP;
        this.VILLE = VILLE;
        this.PAYS = PAYS;
        this.TEL = TEL;
        this.PAYSN = PAYSN;
        this.DDS = DDS;
    }
}