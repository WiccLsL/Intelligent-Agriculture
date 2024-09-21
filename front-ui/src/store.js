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
        // 更新城市信息
        updateCity({ commit }, city) {
            commit('setCity', city);
        },
        // 获取灾害预警信息
        async fetchWeatherWarning(city) {
            try {
                const geoResponse = await axios.get(`https://devapi.qweather.com/v7/geocode/geo?location=${encodeURIComponent(city)}&key=729b0f51f42c44d2863bf51887f44cc3`);
                const locationId = geoResponse.data.location[0].id;

                const warningResponse = await axios.get(`https://devapi.qweather.com/v7/warning/now?location=${locationId}&key=729b0f51f42c44d2863bf51887f44cc3`);
                if (warningResponse.data.code === '200') {
                    commit('setWeatherWarning', warningResponse.data.warning);
                }
            } catch (error) {
                console.error('获取灾害预警信息失败', error);
            }
        }

    },
    getters: {
        weatherInfo: (state) => state.weatherInfo,
        city: (state) => state.city,
        weatherWarning: (state) => state.weatherWarning,
    },

});

// 导出 store
export default store;
