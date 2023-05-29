import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import './css/all.css'
import 'element-ui/lib/theme-chalk/index.css';

import '@/constant/code'
import Cookies from 'js-cookie'
Vue.prototype.$cookies = Cookies;

Vue.config.productionTip = false

Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
