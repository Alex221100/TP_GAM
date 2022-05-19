<template>
    <v-container fluid pa-0>
        <h1>Modification du patient</h1>
        <v-row align="center" justify="center" dense>
            <v-col cols="12" lg="6" md="6">
                <v-card flat tile>
                    <v-text-field 
                        label="IPP" 
                        type='number' 
                        v-model="patient.ipp" 
                        dense
                        disabled
                        filled
                    ></v-text-field>
                
                    <v-select
                      :items="entitledList"
                      v-model="patient.intit"
                      label="Entitled"
                      dense
                      filled
                    ></v-select>

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

                    <date-picker label="Date de naissance" @date="getBirthdayDate" :date="this.patient.ddn"></date-picker>

                    <v-text-field 
                        label="Phone number" 
                        v-model="patient.tel" 
                        dense
                        filled
                    ></v-text-field>
                </v-card>
            </v-col>
            
            
            <v-col cols="12" lg="6" md="6">
                <v-card flat tile>
                    <v-text-field 
                        label="Country of birth" 
                        v-model="patient.paysn" 
                        dense 
                        filled
                    ></v-text-field>
        
                    <v-select
                        v-model="patient.sexe"
                        :items="sexeList"
                        dense
                        filled
                        label="Sexe"
                    ></v-select>

                    <v-text-field 
                        label="Address 1" 
                        v-model="patient.adr1" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Address 2" 
                        v-model="patient.adr2" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Postal Code" 
                        v-model="patient.cp" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="City" 
                        v-model="patient.ville" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Country" 
                        v-model="patient.pays" 
                        dense
                        filled
                    ></v-text-field>
                </v-card>
            </v-col>
        </v-row>

        <h1>Entrée du patient</h1>
        <v-row align="center" justify="center" dense>
            <v-col cols="12" lg="6" md="6">
                <v-card flat tile>
                    <v-text-field 
                        label="UF" 
                        v-model="entry.uf" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="CHAMBRE" 
                        v-model="entry.chambre" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Lit"
                        v-model="entry.lit" 
                        dense
                        filled
                    ></v-text-field>
                </v-card>
            </v-col>
            
            
            <v-col cols="12" lg="6" md="6">
                <v-card flat tile>
                    <v-text-field 
                        label="UFMED" 
                        v-model="entry.ufmed" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="NUMPAS" 
                        v-model="entry.numpas" 
                        dense
                        filled
                    ></v-text-field>

                    <date-picker label="Date d'entrée" @date="getDateEntree" :date="this.entry.date_ENTREE" ></date-picker>

                    <v-switch
                        v-model="closeStay"
                        :label="`Fermer le séjour`"
                    ></v-switch>

                </v-card>
            </v-col>
        </v-row>

        <v-btn elevation="2" @click="updatePatient">Update Patient</v-btn>
    </v-container>
</template>

<script>
import patientService from '../services/patient/patientService'
import DatePicker from '../components/datePicker.vue'

export default({
    data: () => ({
      value: null,
      patient: {},
      entry: {},
      entitledList: ['Monsieur', 'Madame', 'Mademoiselle'],
      sexeList: ['Homme', 'Femme'],
      closeStay: false
    }),
    components:{
        DatePicker
    },
    methods:{
        async getPatientByIpp(){
        try{
          const response = await patientService.getPatientByIpp(this.$route.params.ipp);
          
            this.patient = response.data;
        } catch (error) {
            console.log(error);
        }
        },

        async getEntryByIpp(){
        try{
          const response = await patientService.getEntryByIpp(this.$route.params.ipp);
          
            this.entry = response.data;
            console.log(this.entry);
        } catch (error) {
            console.log(error);
        }
        },
    async updatePatient(){
        try{
            const patientEntry = Object.assign( {}, this.entry, this.patient );
            patientEntry.date_SORTIE = this.closeStay;

            console.log(patientEntry);
            const response = await patientService.updatePatient(patientEntry);
            

            if (response.status == 200) {
                alert("Modification effectuée avec succès.");
                this.$router.push({name: 'Home'});
            }
            
        } catch (error) {
            console.log(error);
        }
    },

    getBirthdayDate (value) {
        console.log(value)
        this.patient.ddn = value;
    },
    getDateEntree(value){
        this.entry.date_ENTREE = value;
    }
    },
    created(){
        this.getPatientByIpp();
        this.getEntryByIpp();
    }
})
</script>