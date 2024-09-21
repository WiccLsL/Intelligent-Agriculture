import Vue from 'vue';
import App from './App.vue';
import router from './router'; // 确保只导入 router
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css';
import 'element-ui/lib/theme-chalk/icon.css';
import store from './store'; // 确保这里使用的是默认导入

Vue.config.productionTip = false;
Vue.use(ElementUI, { size: 'small' });

new Vue({
  router,
  store, // 在这里注册 store
  render: h => h(App),
}).$mount('#app');
