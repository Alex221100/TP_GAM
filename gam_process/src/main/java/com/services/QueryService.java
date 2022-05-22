package com.services;

import com.entity.Patient;
import com.repositories.IPostgreRepository;
import com.services.interfaces.IQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService implements IQueryService {

    @Autowired
    IPostgreRepository postgreRepository;

    public boolean existPatientByIpp(String patientIpp){
        Patient patient = postgreRepository.getPatientByIpp(patientIpp);

        return patient != null;
    }
}
