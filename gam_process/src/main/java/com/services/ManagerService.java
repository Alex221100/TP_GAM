package com.services;

import com.entity.Patient;
import com.repositories.IPostgreRepository;
import com.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService implements IManagerService {

    @Autowired
    public IPostgreRepository postgreRepository;

    public void updatePatient(Patient patient){

        if (postgreRepository.getPatientByIpp(patient.getIPP()) == null){
            //create exception
        }

        int updatedCount = postgreRepository.updatePatient(patient);

        if (updatedCount == 0){
            //error
        }else if (updatedCount >1){
            //error multiple ipp
        }
    }

}
