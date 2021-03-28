<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">

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

        </v-card>
      </v-col>
    </v-row>

  </v-container>
</template>


<script>
export default {
  name: 'Login',
}
</script>