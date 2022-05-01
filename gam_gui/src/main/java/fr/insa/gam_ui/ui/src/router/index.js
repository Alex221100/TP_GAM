import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/:ipp',
    name: 'Update Patient',
    component: () => import('../views/UpdatePatient.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
