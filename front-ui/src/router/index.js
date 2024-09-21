import Vue from 'vue';
import VueRouter from 'vue-router';
import HomeView from '../views/HomeView.vue';
import UserInfo from '@/views/UserInfo.vue';
import UserInfo2 from '@/views/UserInfo2';
import Declaration from '@/views/Declaration.vue';
import PersonalizedPlanting from '@/views/PersonalizedPlanting.vue';
import LatestNews from '@/views/LatestNews.vue';
import CropRecommendation from '@/views/CropRecommendation.vue';
import CropManagement from '@/CropManerge/CropManagement.vue';
import WeatherForecast from '@/views/WeatherForecast.vue';

Vue.use(VueRouter);

// 定义路由
const routes = [
  { path: '/', name: 'home', component: HomeView },
  { path: '/userinfo', name: 'UserInfo', component: UserInfo },
  { path: '/user-info2', name: 'UserInfo2', component: UserInfo2 },
  { path: '/latest-news', name: 'LatestNews', component: LatestNews },
  { path: '/personalized-planting', name: 'PersonalizedPlanting', component: PersonalizedPlanting },
  { path: '/declaration', name: 'Declaration', component: Declaration },
  { path: '/cropRecommendation', name: 'CropRecommendation', component: CropRecommendation },
  { path: '/crop-management', name: 'CropManagement', component: CropManagement },
  { path: '/weather-forecast', name: 'WeatherForecast', component: WeatherForecast },
  { path: '/about', name: 'about', component: () => import(/* webpackChunkName: "about" */ '../views/LoginDialog.vue') }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

// 导出 router
export default router;
