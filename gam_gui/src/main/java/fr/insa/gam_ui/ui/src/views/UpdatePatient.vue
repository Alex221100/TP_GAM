<template>
    <div>
        <v-row align="center"
      justify="center">
        <v-col
          cols="12"
          sm="6"
          md="3"
        >
            <v-text-field 
                label="IPP" 
                type='number' 
                v-model="patient.ipp" 
                dense
                disabled
                filled></v-text-field>
        
            <v-autocomplete
                v-model="value"
                :items="items"
                dense
                filled
                label="Entitled"
            ></v-autocomplete>

            <v-text-field 
                label="Name" 
                v-model="patient.nom" 
                dense
                filled
            ></v-text-field>

            <v-text-field 
                label="Marital Name" 
                v-model="patient.nommar" 
                dense
                filled
            ></v-text-field>

            <v-text-field 
                label="Firstname"
                 v-model="patient.prenom" 
                 dense
                filled
            ></v-text-field>
            //date picker

            <v-text-field label="Phone number" v-model="patient.tel" dense
            filled></v-text-field>
        </v-col>

        

<v-col
          cols="12"
          sm="6"
          md="3"
        >
        <v-text-field label="Country of birth" v-model="patient.paysn" dense
            filled></v-text-field>
        
        <v-autocomplete
                v-model="value"
                :items="items"
                dense
                filled
                label="Sexe"
            ></v-autocomplete>

        <v-text-field label="Address 1" v-model="patient.adr1" dense
            filled></v-text-field>
        <v-text-field label="Address 2" v-model="patient.adr2" dense
            filled></v-text-field>
        <v-text-field label="Postal Code" v-model="patient.cp" dense
            filled></v-text-field>
        <v-text-field label="City" v-model="patient.ville" dense
            filled></v-text-field>
        <v-text-field label="Country" v-model="patient.pays" dense
            filled></v-text-field>
</v-col>
        </v-row>
    </div>
</template>

<script>
import patientService from '../services/patient/patientService'

export default({
    data: () => ({
        items: ['foo', 'bar', 'fizz', 'buzz'],
      values: ['foo', 'bar'],
      value: null,
      patient: {}
    }),
    methods:{
        async getPatientByIpp(){
        try{
          const response = await patientService.getPatientByIpp(this.$route.params.ipp);
          
            this.patient = response.data;
        } catch (error) {
            console.log(error);
        }
    }
    },
    mounted(){
        this.getPatientByIpp();
    }
})
</script>