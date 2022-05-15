<template>
    <div>
        <h1>Identification du patient</h1>
        <v-text-field label="IPP" type='number' v-model="ipp"></v-text-field>
        <v-btn elevation="2" @click="searchPatient">Search</v-btn>
    </div>
    
</template>

<script>
import patientService from '../services/patient/patientService'

export default {
  name: 'Home',

  data() {
      return{
          ipp:null
      }
  },
  methods:{
      async searchPatient(){
        try{
          const response = await patientService.searchPatient(this.ipp);
          
          if (response.data){
              this.$router.push({name: 'Update Patient', params: { ipp: this.ipp }});
          }else{
              this.$router.push({name: 'Create Patient'});
          }
        } catch (error) {
            console.log(error);
        }
    }
  }
}
</script>

