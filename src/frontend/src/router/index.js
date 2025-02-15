import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LearnView from '@/views/LearnView.vue'
import LoginView from '@/views/LoginView.vue'
import CreateAccountView from '@/views/CreateAccountView.vue'
import AccountViewManager from '@/views/AccountViewManager.vue'
import ConfirmAccount from '@/views/ConfirmAccount.vue'
import CompetenceView from '@/views/CompetenceView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/learn',
      name: 'learn',
      component: LearnView,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,

    },
    {
      path: '/signup',
      name: 'signup',
      component: CreateAccountView
    },
    {
      path: '/AccountViewManager',
      name: 'accountViewManager',
      component: AccountViewManager
    },
    {
      path: '/confirmAccount',
      name: 'confirmAccount',
      component: ConfirmAccount
    },
    {
      path: '/competence',
      name: 'competence',
      component: CompetenceView
    }
  ],
})

export default router
