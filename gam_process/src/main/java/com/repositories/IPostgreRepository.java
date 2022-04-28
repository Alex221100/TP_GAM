package com.repositories;

import com.entity.Patient;

public interface IPostgreRepository {
    public Patient getPatientByIpp(String patientIpp);

    public int updatePatient(Patient patient);
}
