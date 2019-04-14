import Vue from 'vue'
import VueRoute from 'vue-router'

import BootstrapVue from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import App from './App.vue'
import Home from './views/Home.vue'
import About from './views/About.vue'
import Support from './views/Support'
import Contact from './views/Contact'

import * as VueGoogleMaps from "vue2-google-maps";

Vue.use(VueGoogleMaps, {
    load: {
        key: "AIzaSyCWAaBJsI1234TI18PITVy7p0Qb6ht123",
        libraries: "places" // necessary for places input
    }
});

Vue.use(BootstrapVue)
Vue.use(VueRoute)
Vue.config.productionTip = false

const routes = [
    {path: '/', component: Home},
    {path: '/about', component: About},
    {path: '/support', component: Support},
    {path: '/contact', component: Contact}
]

const router = new VueRoute({
    routes,
    mode: 'history'
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
