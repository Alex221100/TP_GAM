package com.controllers;

import com.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.services.interfaces.IAdmHL7Service;
import com.services.interfaces.IQueryService;

@RestController
public class PatientController {

    @Autowired
    IQueryService queryService;

    /****************************************
     * Check if a patient exists by its id.
     * @param patientIpp
     * @return Patient
     ****************************************/
    @GetMapping(value="/gam_process/patient/search/{ipp}",produces={"application/json"})
    public ResponseEntity<Boolean> existPatientByIpp(@PathVariable(value = "ipp") String patientIpp) {
        return new ResponseEntity<>(queryService.existPatientByIpp(patientIpp), HttpStatus.OK);
    }
}
