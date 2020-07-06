import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import '@/style/index.css'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// import  { instance } from './api/index'
// Vue.prototype.$http = instance

import Axios from 'axios'
Vue.config.productionTip = false
Axios.defaults.withCredentials = true;
Axios.defaults.baseURL = '/api/'

Vue.use(ElementUI)
Vue.prototype.$http=Axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
