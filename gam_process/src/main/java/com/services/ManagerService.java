package com.services;

import com.entity.Entry;
import com.entity.Patient;
import com.exceptions.NotFoundPatientException;
import com.exceptions.NotModificationException;
import com.exceptions.SameIdentityException;
import com.repositories.IPostgreRepository;
import com.services.interfaces.IManagerService;
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
        }else if (updatedCount > 1){
            System.out.println("exception");
            throw new SameIdentityException();
        }
    }

    public void entryPatient(Entry entry) throws NotModificationException, SameIdentityException, NotFoundPatientException {
        if (postgreRepository.getPatientByIpp(entry.getIPP()) == null){
            throw new NotFoundPatientException();
        }

        entry.setDATE_ENTREE(LocalDate.now().toString());

        int createdCount = postgreRepository.createEntry(entry);

        if (createdCount == 0){
            throw new NotModificationException();
        }else if (createdCount > 1){
            throw new SameIdentityException();
        }
    }

    public Patient getPatientByIpp(String patientIpp){
        Patient patient = postgreRepository.getPatientByIpp(patientIpp);
        return patient;
    }

}
