import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login.vue"
import Home from '../views/Home.vue'
import User from '../views/UserManager.vue'
import Comment from '../views/CommentManager.vue'
import News from '../views/NewsManager.vue'
import Goods from '../views/GoodsManager.vue'
import FeedBack from '../views/BackManager.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect:'login'
  },
  {
    path: '/login',
    name: '登录',
    component: Login
  },
  {
    path: '/home',
    name: '主页',
    component: Home,
    meta:{
      requireAuth: true
    }
  },
  {
    path: '/user',
    name: '用户管理',
    icon: 'el-icon-user-solid',
    show: true,
    component: Home,
    children: [
      {
        path: '/userManager',
        name: '所有用户',
        component: User,
        meta:{
          requireAuth: true
        }
      }
    ]
  },
  {
    path: '/comment',
    name: '问答管理',
    show: true,
    component: Home,
    children: [
      {
        path: '/commentManager',
        name: '所有问答',
        component: Comment,
        meta:{
          requireAuth: true
        }
      }
    ]
  },
  {
    path: '/goods',
    name: '寻物/失物管理',
    show: true,
    component: Home,
    children: [
      {
        path: '/goodsManager',
        name: '所有',
        component: Goods,
        meta:{
          requireAuth: true
        }
      }
    ]
  },
  {
    path: '/feedback',
    name: '反馈管理',
    show: true,
    component: Home,
    children: [
      {
        path: '/backManager',
        name: '反馈',
        component: FeedBack,
        meta:{
          requireAuth: true
        }
      }
    ]
  },
  {
    path: '/news',
    name: '新闻通知管理',
    show: true,
    component: Home,
    children: [
      {
        path: '/newsManager',
        name: '新闻列表',
        component: News,
        meta:{
          requireAuth: true
        }
      }
    ]
  },
]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router
