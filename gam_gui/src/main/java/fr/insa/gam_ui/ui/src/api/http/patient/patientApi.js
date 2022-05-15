import { defaultConfig } from "../httpConfig";
import axios from 'axios';

const baseUrl = 'http://localhost:8081/gam_process';

/**
 * Patient API.
 */
export default class patientApi {

  
    /**
     * Search if a patient exists by its IPP.
     */
    static async searchPatient(ipp) {

      return axios.get(`${baseUrl}/patient/search/${ipp}`, defaultConfig);
    }

    /**
     * Get patient by its IPP.
     */
    static async getPatientByIpp(ipp){
      return axios.get(`${baseUrl}/patient/${ipp}`, defaultConfig);
    }

    /**
     * Get patient by its IPP.
     */
     static async getEntryByIpp(ipp){
      return axios.get(`${baseUrl}/entry/${ipp}`, defaultConfig);
    }

    /**
     * Update patient.
     */
     static async updatePatient(patient){
      return axios.put(`${baseUrl}/patient/update`,patient, defaultConfig);
    }

    /**
     * Create patient.
     */
     static async createPatient(patient){
      return axios.post(`${baseUrl}/patient/create`,patient, defaultConfig);
    }
}