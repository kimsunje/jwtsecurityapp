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

          <v-card-title>Sign Up</v-card-title>

          <div id="sign">
            <v-text-field
              v-model="email"
              label="E-Mail"
              maxlength="40"
              :rules="emailvalidation"
            ></v-text-field>

            <v-text-field
              :type="'password'"
              v-model="password"
              label="Password"
              :rules="passvalidation"
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
              type="submit"
              @click="insertUserInfo"
            >
              SignUp
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>

  </v-container>
</template>


<script>
export default {
  name: 'SignUp',
  data: () => ({
    emailvalidation: [
      value => !!value || '이메일은 필수 입력 항목입니다.',
    ],
    passvalidation: [
      value => (value && value.length >= 8) || '비밀번호는 최소 8자리 이상입니다.',
    ]
  }),
  methods: {
    insertUserInfo: function () {
      if (!this.email || !this.password) {
        alert('이메일 또는 비밀번호를 입력해주세요');
        return false;
      } else {
        this.$axios.post('/user/signUp',
          { email: this.email, pw: this.password }
        ).then(response => {
          if (response.status === 200) {
            alert('Sign Up Success')
            this.$router.push({ name: 'Login' })
          } else {
            alert(response.data)
          }
        }).catch((exception) => {
          alert(exception)
        })
      }
    }

  }
}

</script>