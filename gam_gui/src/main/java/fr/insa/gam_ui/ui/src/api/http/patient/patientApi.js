import { defaultConfig } from "../httpConfig";
import axios from 'axios';

const baseUrl = 'http://localhost:8081/gam_process/patient';

/**
 * Patient API.
 */
export default class patientApi {

  
    /**
     * Search if a patient exists by its IPP.
     */
    static async searchPatient(ipp) {

      return axios.get(`${baseUrl}/search/${ipp}`, defaultConfig);
    }

    /**
     * Get patient by its IPP.
     */
    static async getPatientByIpp(ipp){
      return axios.get(`${baseUrl}/${ipp}`, defaultConfig);
    }
}