package com.controllers;

import com.model.PatientEntry;
import com.entity.Entry;
import com.entity.Patient;
import com.exceptions.NotFoundPatientException;
import com.exceptions.NotModificationException;
import com.exceptions.SameIdentityException;
import com.services.interfaces.IManagerService;
import com.utils.PatientParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.services.interfaces.IAdmHL7Service;
import com.services.interfaces.IQueryService;

import java.util.logging.Logger;

@RestController
public class PatientController {

    @Autowired
    IQueryService queryService;

    @Autowired
    IManagerService managerService;

    @Autowired
    IAdmHL7Service admHL7Service;

    Logger logger = Logger.getLogger("Gam_Logger");

    /****************************************
     * Check if a patient exists by its ipp.
     * @param patientIpp
     * @return Patient
     ****************************************/
    @CrossOrigin(origins = "*")
    @GetMapping(value="/gam_process/patient/search/{ipp}",produces={"application/json"})
    public ResponseEntity<Boolean> existPatientByIpp(@PathVariable(value = "ipp") String patientIpp) {
        return new ResponseEntity<>(queryService.existPatientByIpp(patientIpp), HttpStatus.OK);
    }

    /****************************************
     * Get patient by its ipp.
     * @param patientIpp
     * @return Patient
     ****************************************/
    @CrossOrigin(origins = "*")
    @GetMapping(value="/gam_process/patient/{ipp}",produces={"application/json"})
    public ResponseEntity<Patient> getPatientByIpp(@PathVariable(value = "ipp") String patientIpp) {
        return new ResponseEntity<>(managerService.getPatientByIpp(patientIpp), HttpStatus.OK);
    }

    /****************************************
     * Get patient by its ipp.
     * @param patientIpp
     * @return Patient
     ****************************************/
    @CrossOrigin(origins = "*")
    @GetMapping(value="/gam_process/entry/{ipp}",produces={"application/json"})
    public ResponseEntity<Entry> getEntryByIpp(@PathVariable(value = "ipp") String patientIpp) {
        return new ResponseEntity<>(managerService.getEntryByIpp(patientIpp), HttpStatus.OK);
    }

    /****************************************
     * Update a patient
     * @param patientEntry
     ****************************************/
    @CrossOrigin(origins = "*")
    @PutMapping(value="/gam_process/patient/update",produces={"application/json"})
    public ResponseEntity<String> updatePatient(@RequestBody PatientEntry patientEntry) throws NotModificationException, SameIdentityException, NotFoundPatientException {
        try {
            logger.info("Begin updated og a patient and the entry");

            Patient patient = PatientParser.toPatient(patientEntry);
            managerService.updatePatient(patient);
            logger.info("Patient updated");

            Entry entry = PatientParser.toEntry(patientEntry);
            managerService.updateEntry(entry);
            logger.info("Entry updated");
            return new ResponseEntity<>("{\"response\":\"patient updated\"}", HttpStatus.OK);
        }catch(NotModificationException e){
            logger.info("Error with the database during the updated");
            return new ResponseEntity<>("{\"response : error with the database\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(SameIdentityException e){
            logger.info("Many patients have the same ipp in the database");
            return new ResponseEntity<>("{\"response\":\"many patients have the same ipp\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(NotFoundPatientException e) {
            logger.info("The ipp is not found");
            return new ResponseEntity<>("{\"response\":\"patient with this ipp is not found\"}", HttpStatus.NOT_FOUND);
        }
    }

    /****************************************
     * Create a patient
     * @param patient
     ****************************************/
    @CrossOrigin(origins = "*")
    @PostMapping(value="/gam_process/patient/create",produces={"application/json"})
    public ResponseEntity<String> createPatient(@RequestBody PatientEntry patient) {
        try {
            logger.info("Begin the creation of a patient");
            Boolean test = admHL7Service.create_hl7(patient);
            logger.info("Creation successful");
            return new ResponseEntity<>("{\"response\":\"patient created with ipp =" + test + "}", HttpStatus.OK);
        }catch(Exception e){
            logger.info("Error with the database during the created");
            return new ResponseEntity<>("{\"response : error with the database\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
