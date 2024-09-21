import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import UserInfo from '@/views/UserInfo.vue'
import UserInfo2 from '@/views/UserInfo2';
import Declaration from '@/views/Declaration.vue';
import PersonalizedPlanting from '@/views/PersonalizedPlanting.vue';
import LatestNews from '@/views/LatestNews.vue';
import CropRecommendation from '@/views/CropRecommendation.vue';
import CropManagement from '@/CropManerge/CropManagement.vue';
import CropEdit from '@/CropManerge/CropEdit.vue'  // 你的编辑页面组件



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/userinfo',
    name: 'UserInfo',
    component: UserInfo
  },
  {
    path: '/user-info2', // 新的路径
    name: 'UserInfo2',
    component: UserInfo2
  },
  {
    path: '/latest-news',
    name: 'LatestNews',
    component: LatestNews,
  },
  {
    path: '/personalized-planting',
    name: 'PersonalizedPlanting',
    component: PersonalizedPlanting // 新的路径
  },
  {
    path: '/declaration',
    name: 'Declaration',
    component: Declaration
  },
  {
    path: '/cropRecommendation',
    name: 'CropRecommendation',
    component: CropRecommendation
  },
  {
    path: '/crop-management',
    name: 'CropManagement',
    component: CropManagement
  },
  {
    path: '/edit-crop/:id',
    name: 'CropEdit',
    component: CropEdit,
    props: true
  },  {
    path: '/Gao-de/:id',
    name: 'Gaode',
    component: Gaode,
    props: true
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginDialog.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
