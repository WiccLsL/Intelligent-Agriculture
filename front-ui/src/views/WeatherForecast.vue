<template>
  <div>
    <div class="weather-info" v-if="weatherInfo">
      <div class="loc">{{ weatherInfo.city }}</div>

      <div v-for="(cast, index) in weatherInfo.casts" :key="index">
        <div class="date">{{ cast.date }}</div>
        <div class="weather">
          <i v-if="cast.dayweather === '晴'" class="el-icon-sunny"></i>
          <i v-if="cast.dayweather === '阴'" class="el-icon-cloudy"></i>
          <i v-if="cast.dayweather === '多云'" class="el-icon-partly-cloudy"></i>
          <i v-if="cast.dayweather === '晴间多云'" class="el-icon-cloudy-and-sunny"></i>
          白天天气: {{ cast.dayweather }}
        </div>
        <div class="weather">
          <i v-if="cast.nightweather === '晴'" class="el-icon-moon"></i>
          <i v-if="cast.nightweather === '阴'" class="el-icon-cloudy"></i>
          <i v-if="cast.nightweather === '多云'" class="el-icon-partly-cloudy"></i>
          <i v-if="cast.nightweather === '晴间多云'" class="el-icon-cloudy-and-sunny"></i>
          夜间天气: {{ cast.nightweather }}
        </div>
        <div class="temperature">
          <i class="el-icon-sunny"></i>
          白天温度: {{ cast.daytemp }}°C
        </div>
        <div class="temperature">
          <i class="el-icon-moon"></i>
          夜间温度: {{ cast.nighttemp }}°C
        </div>
        <div class="temperature">
          <i class="el-icon-loading"></i>
          白天风力: {{ cast.daypower }}级
        </div>
        <div class="temperature">
          <i class="el-icon-loading"></i>
          夜间风力: {{ cast.nightpower }}级
        </div>
      </div>
      <div v-if="weatherWarning.length > 0">
        <h3>灾害预警</h3>
        <ul>
          <li v-for="(warning, index) in weatherWarning" :key="index">{{ warning.text }}</li>
        </ul>
      </div>
    </div>
  </div>

</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
  computed: {
    ...mapGetters(['city', 'weatherInfo', 'weatherWarning']),
  },
  methods: {
    ...mapActions(['fetchWeatherWarning']),
  },
  mounted() {
    const city = "南京"; // 这里的 city 是一个字符串
    console.log("组件中 city 的值:", city); // 确保输出的是字符串
    this.fetchWeatherWarning(city); // 调用获取预警信息
  }
};
</script>
<style scoped>
/* 在这里添加样式 */
</style>
