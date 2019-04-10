import Vue from 'vue'
import VueRoute from 'vue-router'

import BootstrapVue from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import App from './App.vue'
import Home from './components/Home'
import Link1 from './components/Link1'

Vue.use(BootstrapVue)
Vue.use(VueRoute)
Vue.config.productionTip = false

const routes = [
    {path: '/', component: Home},
    {path: '/link1/:link1Id', component: Link1}
]

const router = new VueRoute({
    routes,
    mode: 'history'
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
