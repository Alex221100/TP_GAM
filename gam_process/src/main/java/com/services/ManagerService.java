package com.services;

import com.entity.Entry;
import com.entity.Patient;
import com.exceptions.NotFoundPatientException;
import com.exceptions.NotModificationException;
import com.exceptions.SameIdentityException;
import com.repositories.IPostgreRepository;
import com.services.interfaces.IManagerService;
import com.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ManagerService implements IManagerService {

    @Autowired
    public IPostgreRepository postgreRepository;

    public void updatePatient(Patient patient) throws NotModificationException, NotFoundPatientException, SameIdentityException {

        if (postgreRepository.getPatientByIpp(patient.getIPP()) == null){
            throw new NotFoundPatientException();
        }

        int updatedCount = postgreRepository.updatePatient(patient);
        System.out.println("ici");
        System.out.println(updatedCount);
        if (updatedCount == 0){
            throw new NotModificationException();
        }
    }

    public void updateEntry(Entry entry) throws NotModificationException, SameIdentityException, NotFoundPatientException {
        if (postgreRepository.getPatientByIpp(entry.getIPP()) == null){
            throw new NotFoundPatientException();
        }

        /*if (entry.getDATE_SORTIE() == 'null'){
            entry.setDATE_SORTIE(null);
        }*/

        int updatedCount = postgreRepository.updateEntry(entry);
        System.out.println(updatedCount);
        if (updatedCount == 0){
            throw new NotModificationException();
        }
    }

    public Patient getPatientByIpp(String patientIpp){
        return postgreRepository.getPatientByIpp(patientIpp);
    }

    public Entry getEntryByIpp(String patientIpp){
        return postgreRepository.getEntryByIpp(patientIpp);
    }

}
