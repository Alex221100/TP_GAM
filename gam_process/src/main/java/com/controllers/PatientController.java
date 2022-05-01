package com.controllers;

import com.entity.Entry;
import com.entity.Patient;
import com.exceptions.NotFoundPatientException;
import com.exceptions.NotModificationException;
import com.exceptions.SameIdentityException;
import com.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.services.interfaces.IAdmHL7Service;
import com.services.interfaces.IQueryService;

@RestController
public class PatientController {

    @Autowired
    IQueryService queryService;

    @Autowired
    IManagerService managerService;

    @Autowired
    IAdmHL7Service admHL7Service;

    /****************************************
     * Check if a patient exists by its ipp.
     * @param patientIpp
     * @return Patient
     ****************************************/
    @GetMapping(value="/gam_process/patient/search/{ipp}",produces={"application/json"})
    public ResponseEntity<Boolean> existPatientByIpp(@PathVariable(value = "ipp") String patientIpp) {
        return new ResponseEntity<>(queryService.existPatientByIpp(patientIpp), HttpStatus.OK);
    }

    /****************************************
     * Check if a patient exists by its ipp.
     * @param patientIpp
     * @return Patient
     ****************************************/
    @GetMapping(value="/gam_process/patient/{ipp}",produces={"application/json"})
    public ResponseEntity<Patient> getPatientByIpp(@PathVariable(value = "ipp") String patientIpp) {
        return new ResponseEntity<>(managerService.getPatientByIpp(patientIpp), HttpStatus.OK);
    }

    /****************************************
     * Update a patient
     * @param patient
     ****************************************/
    @PostMapping(value="/gam_process/patient/update",produces={"application/json"})
    public ResponseEntity<String> updatePatient(@RequestBody Patient patient) throws NotModificationException, SameIdentityException, NotFoundPatientException {
        try {
            managerService.updatePatient(patient);
            return new ResponseEntity<>("{\"response\":\"patient updated\"}", HttpStatus.OK);
        }catch(NotModificationException e){
            return new ResponseEntity<>("{\"response : error with the database\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(SameIdentityException e){
            return new ResponseEntity<>("{\"response\":\"many patients have the same ipp\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(NotFoundPatientException e) {
            return new ResponseEntity<>("{\"response\":\"patient with this ipp is not found\"}", HttpStatus.NOT_FOUND);
        }
    }

    /****************************************
     * Entry a patient
     * @param entry
     ****************************************/
    @PutMapping(value="/gam_process/patient/entry",produces={"application/json"})
    public ResponseEntity<String> updatePatient(@RequestBody Entry entry) throws NotModificationException, SameIdentityException, NotFoundPatientException {
        try {
            managerService.entryPatient(entry);
            return new ResponseEntity<>("{\"response\":\"entry accepted\"}", HttpStatus.OK);
        }catch(NotModificationException e){
            return new ResponseEntity<>("{\"response : error with the database\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(SameIdentityException e){
            return new ResponseEntity<>("{\"response\":\"many patients have the same ipp\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(NotFoundPatientException e) {
            return new ResponseEntity<>("{\"response\":\"patient with this ipp is not found\"}", HttpStatus.NOT_FOUND);
        }
    }

    /****************************************
     * Create a patient
     * @param patient
     ****************************************/
    @PostMapping(value="/gam_process/patient/create",produces={"application/json"})
    public ResponseEntity<String> createPatient(@RequestBody Patient patient) {
        try {
            String ipp = admHL7Service.createPatient(patient);
            return new ResponseEntity<>("{\"response\":\"patient created with ipp =" + ipp + "}", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("{\"response : error with the database\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
