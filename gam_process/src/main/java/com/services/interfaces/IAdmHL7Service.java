package com.services.interfaces;


import com.model.PatientEntry;

import java.io.IOException;

public interface IAdmHL7Service {
    public Boolean create_hl7(PatientEntry patient) throws Exception;
}
