<template>
    <v-container fluid pa-0>
        <h1>Création du patient</h1>
        <v-row align="center" justify="center" dense>
            <v-col cols="12" lg="6" md="6">
                <v-card flat tile>
                    <v-select
                        v-model="patientEntry.intit"
                        :items="entitledList"
                        dense
                        filled
                        label="Entitled"
                    ></v-select>

                    <v-text-field 
                        label="Name" 
                        v-model="patientEntry.nom" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Marital Name" 
                        v-model="patientEntry.nommar" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Firstname"
                        v-model="patientEntry.prenom" 
                        dense
                        filled
                    ></v-text-field>
                    
                    <date-picker label="Date de naissance" @date="getBirthdayDate"></date-picker>


                    <v-text-field 
                        label="Phone number" 
                        v-model="patientEntry.tel" 
                        dense
                        filled
                    ></v-text-field>
                </v-card>
            </v-col>
            
            
            <v-col cols="12" lg="6" md="6">
                <v-card flat tile>
                    <v-text-field 
                        label="Country of birth" 
                        v-model="patientEntry.paysn" 
                        dense 
                        filled
                    ></v-text-field>
        
                    <v-select
                        v-model="patientEntry.sexe"
                        :items="sexeList"
                        dense
                        filled
                        label="Sexe"
                    ></v-select>

                    <v-text-field 
                        label="Address 1" 
                        v-model="patientEntry.adr1" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Address 2" 
                        v-model="patientEntry.adr2" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Postal Code" 
                        v-model="patientEntry.cp" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="City" 
                        v-model="patientEntry.ville" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Country" 
                        v-model="patientEntry.pays" 
                        dense
                        filled
                    ></v-text-field>
                </v-card>
            </v-col>
        </v-row>

        <h1>Ajout d'une entrée</h1>
        <v-row align="center" justify="center" dense>
            <v-col cols="12" lg="6" md="6">
                <v-card flat tile>
                    <v-text-field 
                        label="UF" 
                        v-model="patientEntry.uf" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="CHAMBRE" 
                        v-model="patientEntry.chambre" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="Lit"
                        v-model="patientEntry.lit" 
                        dense
                        filled
                    ></v-text-field>
                </v-card>
            </v-col>
            
            
            <v-col cols="12" lg="6" md="6">
                <v-card flat tile>
                    <v-text-field 
                        label="UFMED" 
                        v-model="patientEntry.ufmed" 
                        dense
                        filled
                    ></v-text-field>

                    <v-text-field 
                        label="NUMPAS" 
                        v-model="patientEntry.numpas" 
                        dense
                        filled
                    ></v-text-field>
                </v-card>
            </v-col>
        </v-row>

        <v-btn elevation="2" @click="createPatient">Create Patient</v-btn>
    </v-container>
</template>

<script>
import DatePicker from '../components/datePicker.vue'
import patientService from '../services/patient/patientService'

export default({
    data: () => ({
      patientEntry: {},
      entitledList: ['Monsieur', 'Madame', 'Mademoiselle'],
      sexeList: ['Homme', 'Femme']
    }),
    components:{
        DatePicker
    },

    methods:{

    async createPatient(){
        try{
            const patientEntry = Object.assign( {}, this.entry, this.patientEntry );

            console.log(patientEntry);
            const response = await patientService.createPatient(patientEntry);
            

            if (response.status == 200) {
                alert("Création effectuée avec succès.");
                this.$router.push({name: 'Home'});
            }
            
        } catch (error) {
            console.log(error);
        }
    },
    getBirthdayDate (value) {
        console.log(value)
        this.patientEntry.ddn = value;
    },
}
})
</script>
