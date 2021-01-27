<template>
  <v-container>
    <v-row justify="center">
      <v-card
        :loading="loading"
        class="mx-auto my-12"
        max-width="374"
      >
        <template slot="progress">
          <v-progress-linear
            color="deep-purple"
            height="10"
            indeterminate
          ></v-progress-linear>
        </template>

        <v-card-title>User Login</v-card-title>

        <div>
          <v-text-field
            v-model="title"
            label="E-Mail"
            maxlength="40"
          ></v-text-field>

          <v-text-field
            :type="'password'"
            name="input-10-2"
            label="Password"
            @click:append="show3 = !show3"
          ></v-text-field>
        </div>

        <v-divider class="mx-4"></v-divider>

        <v-card-title>Tonight's availability</v-card-title>

        <v-card-text>
          <v-chip-group
            v-model="selection"
            active-class="deep-purple accent-4 white--text"
            column
          >
            <v-chip>5:30PM</v-chip>

            <v-chip>7:30PM</v-chip>

            <v-chip>8:00PM</v-chip>

            <v-chip>9:00PM</v-chip>
          </v-chip-group>
        </v-card-text>

        <v-card-actions>
          <v-btn
            color="deep-purple lighten-2"
            text
            @click="reserve"
          >
            Reserve
          </v-btn>
        </v-card-actions>
      </v-card>

    </v-row>

  </v-container>
</template>


<script>
export default {
  name: 'Login',
}
</script>