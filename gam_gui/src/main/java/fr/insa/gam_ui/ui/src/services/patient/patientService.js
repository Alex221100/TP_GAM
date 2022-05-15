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

      /**
     * Get entry by its IPP.
     */
     static async getEntryByIpp(ipp){
      return await patientApi.getEntryByIpp(ipp);
    }

    /**
     * Update patient.
     */
     static async updatePatient(patient){
        return await patientApi.updatePatient(patient);
      }

      /**
     * Create patient.
     */
     static async createPatient(patient){
      return await patientApi.createPatient(patient);
    }
}