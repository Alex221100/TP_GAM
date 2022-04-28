package com.services.interfaces;

import com.entity.Patient;

public interface IQueryService {

    public boolean existPatientByIpp(String patientIpp);
}
