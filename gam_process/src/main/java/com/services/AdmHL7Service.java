package com.services;

import com.entity.Patient;
import com.repositories.IPostgreRepository;
import com.services.interfaces.IAdmHL7Service;
import org.springframework.stereotype.Service;

@Service
public class AdmHL7Service implements IAdmHL7Service {
    public IPostgreRepository postgreRepository;

}
