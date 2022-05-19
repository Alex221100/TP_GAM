package com.utils;

import com.entity.Entry;
import com.entity.Patient;
import com.model.PatientEntry;

import java.time.LocalDate;

public class PatientParser {
    public static Patient toPatient(PatientEntry patientEntry){

        return new Patient(patientEntry.getIPP(), patientEntry.getNOM(), patientEntry.getPRENOM(), patientEntry.getINTIT()
                , patientEntry.getNOMMAR(), patientEntry.getDDN(), patientEntry.getSEXE(), patientEntry.getADR1(),
                patientEntry.getADR2(), patientEntry.getCP(), patientEntry.getVILLE(), patientEntry.getPAYS(),
                patientEntry.getTEL(),patientEntry.getPAYSN());
    }

    public static Entry toEntry(PatientEntry patientEntry){
        String releaseDate = patientEntry.getDATE_SORTIE() ? LocalDate.now().toString() : null;
        return new Entry(patientEntry.getIPP(), patientEntry.getIEP(), patientEntry.getDATE_ENTREE(),
                releaseDate, patientEntry.getUF(), patientEntry.getCHAMBRE(),
                patientEntry.getLIT(), patientEntry.getUFMED(), patientEntry.getNUMPAS());
    }
}
