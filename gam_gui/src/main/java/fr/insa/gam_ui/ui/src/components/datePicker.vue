<template>
  <v-container>
    
        <v-menu
          ref="menu1"
          v-model="menu1"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="date"
              :label=label
              persistent-hint
              prepend-icon="mdi-calendar"
              v-bind="attrs"
              v-on="on"
              filled
              dense
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="date"
            no-title
            @input="menu1 = false"
            @change="sendDatePicker"
          ></v-date-picker>
        </v-menu>
  </v-container>
</template>

<script>
  export default {
    data (){
      return {
        menu1: false,
        menu2: false,
      }
    },

    props:{
      label: String,
      date: String
    },

    computed: {
      computedDateFormatted () {
        return this.formatDate(this.date)
      },
    },

    methods: {
      formatDate (date) {
        if (!date) return null

        const [year, month, day] = date.split('-')
        return `${month}/${day}/${year}`
      },
      parseDate (date) {
        if (!date) return null

        const [month, day, year] = date.split('/')
        return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
      },
      sendDatePicker(){
        this.$emit('date', this.date);
      }
    },
  }
</script>