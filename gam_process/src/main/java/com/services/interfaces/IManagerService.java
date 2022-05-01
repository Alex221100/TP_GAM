package com.services.interfaces;

import com.entity.Entry;
import com.entity.Patient;
import com.exceptions.NotFoundPatientException;
import com.exceptions.NotModificationException;
import com.exceptions.SameIdentityException;

public interface IManagerService {
    public void updatePatient(Patient patient) throws NotModificationException, NotFoundPatientException, SameIdentityException;

    public void entryPatient(Entry entry) throws NotModificationException, SameIdentityException, NotFoundPatientException;

    public Patient getPatientByIpp(String patientIpp);
}
