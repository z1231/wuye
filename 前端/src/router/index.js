import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/login.vue'

Vue.use(VueRouter)


const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/layout',
    name: 'layout',
    component: () => import( '@/views/layout.vue'),
    redirect: '/layout/position',
    children: [
      {
        path: 'home',
        component: () => import('@/views/Home.vue'),
        name: 'home'
      },
      {
        path: 'position',
        component: () => import('@/views/property/personnel/position.vue'),
        name: 'position'
      },{
        path: 'attendance',
        component: () => import('@/views/property/personnel/attendance.vue'),
        name: 'attendance'
      },{
        path: 'staff',
        component: () => import('@/views/property/personnel/staff.vue'),
        name: 'staff'
      },{
        path: 'repair',
        component: () => import('@/views/property/service/repair.vue'),
        name: 'repair'
      }, {
        path: 'car',
        component: () => import('@/views/property/service/car.vue'),
        name: 'car'
      },{
        path: 'pay',
        component: () => import('@/views/property/service/pay.vue'),
        name: 'pay'
      },{
        path: 'storehouse',
        component: () => import('@/views/property/service/storehouse.vue'),
        name: 'storehouse'
      },{
        path: 'scheduling',
        component: () => import('@/views/property/security/scheduling.vue'),
        name: 'scheduling'
      },{
        path: 'tips',
        component: () => import('@/views/property/community/tips.vue'),
        name: 'tips'
      },{
        path: 'proposal',
        component: () => import('@/views/property/community/proposal.vue'),
        name: 'proposal'
      },{
        path: 'user',
        component: () => import('@/views/property/design/user.vue'),
        name: 'user'
      },{
        path: 'information',
        component: () => import('@/views/property/design/information.vue'),
        name: 'information'
      }
    ]
  },
  {
    path: '/userLayout',
    name: 'userLayout',
    component: () => import( '@/views/userLayout.vue'),
    redirect: '/userLayout/repair',
    children: [
      {
        path: 'repair',
        component: () => import('@/views/user/repair.vue'),
        name: 'repair'
      },{
        path: 'pay',
        component: () => import('@/views/user/pay.vue'),
        name: 'pay'
      },{
        path: 'opinion',
        component: () => import('@/views/user/opinion.vue'),
        name: 'opinion'
      }
    ]
  },
  {
    path: '/personnelLayout',
    name: 'userLayout',
    component: () => import( '@/views/personnelLayout.vue'),
    redirect: '/personnelLayout/repair',
    children: [
      {
        path: 'repair',
        component: () => import('@/views/personnel/repair.vue'),
        name: 'repair'
      }
    ]
  },
  {
    path: '/payModal',
    name: 'payModal',
    component: () => import( '@/components/payModal.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
