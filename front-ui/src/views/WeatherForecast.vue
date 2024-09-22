<template>
  <div class="container">
    <div class="left-panel">
    <div class="weather-info" v-if="weatherInfo">
      <h3 class="header">实时天气及未来三天预测</h3>
      <div class="loc">{{ weatherInfo.city }}</div>

      <div v-for="(cast, index) in weatherInfo.casts" :key="index" class="forecast-item">
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
      </div>
    </div>

    <div class="right-panel">
      <div class="weather-warning" v-if="warningData.length > 0">
        <h3>灾害预警信息</h3>
        <div v-for="(warning, index) in warningData" :key="index" class="warning-item">
          <div class="warning-title">{{ warning.title }}</div>
          <div class="warning-level">预警等级: <span :style="{ color: warning.severityColor }">{{ warning.level }}</span></div>
          <div class="warning-time">
            <span>发布时间: {{ warning.pubTime }}</span>
            <span>有效期: {{ warning.startTime }} - {{ warning.endTime }}</span>
          </div>
          <div class="warning-text">{{ warning.text }}</div>
        </div>

    </div>
    </div>
  </div>
</template>




<script>
import { mapGetters, mapActions } from 'vuex';
import axios from "axios";
export default {
  data() {
    return {
      warningData: [],
    };
  },
  computed: {
    ...mapGetters(['city', 'weatherInfo', 'weatherWarning']),
  },
  methods: {
    // 获取灾害预警信息
    // 获取灾害预警信息
    async fetchWeatherWarning() {
      const locationId = "118.68,32.16";
      try {
        const warningResponse = await axios.get(`https://devapi.qweather.com/v7/warning/now?location=${locationId}&key=729b0f51f42c44d2863bf51887f44cc3`);
        this.warningData = warningResponse.data.warning || []; // 使用 this 访问 warningData
        console.log(this.warningData); // 调试输出
      } catch (error) {
        console.error('获取灾害预警信息失败', error);
      }
    }
  },
  mounted() {
    this.fetchWeatherWarning(); // 调用获取预警信息
  },
};
</script>
<style scoped src="./WeatherForecast.css"></style>
