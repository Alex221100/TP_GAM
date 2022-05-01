import patientApi from '@/api/http/patient/patientApi'

/**
 * Patient Service.
 */
export default class patientService {

    /**
    * Search if a patient exists by its IPP.
    */
    static async searchPatient(ipp) {
        return await patientApi.searchPatient(ipp);
    }

    /**
     * Get patient by its IPP.
     */
     static async getPatientByIpp(ipp){
        return await patientApi.getPatientByIpp(ipp);
      }
}