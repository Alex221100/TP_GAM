<template>
    <v-container fluid pa-0>
        <v-row align="center" justify="center" 
            style="height:100vh" dense>
            <v-col cols="12" lg="6" md="6">
                <v-card flat tile>
                    <v-select
                      :items="entitledList"
                      v-model="entitled"
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
                    //date picker

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
        
                    <v-autocomplete
                        v-model="value"
                        :items="items"
                        dense
                        filled
                        label="Sexe"
                    ></v-autocomplete>

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

        <v-row>
            <v-btn elevation="2" @click="updatePatient">Create Patient</v-btn>
        </v-row>
    </v-container>
</template>

<script>

export default({
    data: () => ({
      patient: {}
    }),
})
</script>
