import Vue from 'vue';
import axios from 'axios';

// 设置 Axios 默认基础 URL
axios.defaults.baseURL = 'http://localhost:8082'; // 根据你的 Spring Boot 服务的 URL 修改
Vue.prototype.$http = axios;

new Vue({
    render: h => h(App),
}).$mount('#app');
