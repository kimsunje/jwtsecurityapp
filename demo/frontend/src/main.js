import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router/index'
import axios from 'axios'



axios.interceptors.request.use(
  (config) => {
    let token = localStorage.getItem('authorization')

    if (token) {
      config.headers['Authorization'] = token
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

Vue.prototype.$axios = axios
Vue.config.productionTip = false

new Vue({
  vuetify,
  render: h => h(App),
  router,

}).$mount('#app')

