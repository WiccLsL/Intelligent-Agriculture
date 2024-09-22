import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

// 定义 Vuex Store
const store = new Vuex.Store({
    state: {
        weatherInfo: null,       // 存储天气信息
        weatherWarning: [],      // 存储灾害预警信息
        city: '',                // 存储城市信息
    },
    mutations: {
        setWeatherInfo(state, weatherInfo) {
            state.weatherInfo = weatherInfo;
        },
        setCity(state, city) {
            state.city = city;
        },
        setWeatherWarning(state, weatherWarning) {
            state.weatherWarning = weatherWarning;
        },
    },
    actions: {
        // 更新天气信息
        updateWeatherInfo({ commit }, weatherInfo) {
            commit('setWeatherInfo', weatherInfo);
        },
    },
    getters: {
        weatherInfo: (state) => state.weatherInfo,
        city: (state) => state.city,
        weatherWarning: (state) => state.weatherWarning,
    },

});

// 导出 store
export default store;
