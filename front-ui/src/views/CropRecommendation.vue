<template>
  <div>
    <h1>作物推荐</h1>
    <form @submit.prevent="getRecommendation">
      <div>
        <label for="N">氮含量 (N):</label>
        <input type="number" id="N" v-model.number="inputData.N" required />
      </div>
      <div>
        <label for="P">磷含量 (P):</label>
        <input type="number" id="P" v-model.number="inputData.P" required />
      </div>
      <div>
        <label for="K">钾含量 (K):</label>
        <input type="number" id="K" v-model.number="inputData.K" required />
      </div>
      <div>
        <label for="temperature">温度:</label>
        <input type="number" id="temperature" v-model.number="inputData.temperature" step="0.1" required />
      </div>
      <div>
        <label for="humidity">湿度:</label>
        <input type="number" id="humidity" v-model.number="inputData.humidity" required />
      </div>
      <div>
        <label for="ph">pH 值:</label>
        <input type="number" id="ph" v-model.number="inputData.ph" step="0.1" required />
      </div>
      <div>
        <label for="rainfall">降雨量:</label>
        <input type="number" id="rainfall" v-model.number="inputData.rainfall" required />
      </div>
      <button type="submit">获取推荐</button>
    </form>
    <div v-if="recommendation">
      <h2>推荐的作物: {{ recommendation }}</h2>
    </div>

    <h2>实时天气查询</h2>
    <div>
      <label for="city">城市名:</label>
      <input type="text" id="city" v-model="cityName" placeholder="输入城市名" />
      <button @click="getWeather">获取天气</button>
    </div>
    <div v-if="weather">
      <h3>天气数据</h3>
      <p>温度: {{ weather.temperature }}°C</p>
      <p>湿度: {{ weather.humidity }}%</p>
      <p>风速: {{ weather.windSpeed}} m/s</p>
      <p>天气状况: {{ weather.description }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import md5 from 'md5';

export default {
  data() {
    return {
      inputData: {
        N: null,
        P: null,
        K: null,
        temperature: null,
        humidity: null,
        ph: null,
        rainfall: null
      },
      recommendation: null,
      cityName: '',
      weather: null
    };
  },
  methods: {
    async getRecommendation() {
      try {
        const response = await axios.post('http://localhost:9090/api/crop/recommend', this.inputData);
        this.recommendation = response.data;
      } catch (error) {
        console.error('获取推荐失败:', error);
      }
    },
    async getWeather() {
      try {
        // 第一步: 将城市名称从中文转换为英文
        const translatedCityName = await this.translateCityName(this.cityName);
        console.log(`Translated city name: ${translatedCityName}`);

        // 第二步: 使用翻译后的城市名称进行天气查询
        const response = await axios.get(`http://localhost:9090/api/weather/city/${translatedCityName}`);
        this.weather = response.data;
      } catch (error) {
        console.error('获取天气失败:', error);
        this.weather = null;
      }
    },
    async translateCityName(chineseName) {
      const appId = '20240905002142245'; // 替换为你的百度应用 ID
      const apiKey = 'Qnn4tesx8rd4_QbfXcX4'; // 替换为你的百度 API 密钥
      const url = 'https://fanyi-api.baidu.com/api/trans/vip/translate';
      const salt = Date.now(); // 随机数
      const query = chineseName; // 待翻译文本
      const sign = md5(`${appId}${query}${salt}${apiKey}`); // 生成签名

      console.log(`Query: ${query}`);
      console.log(`Salt: ${salt}`);
      console.log(`Sign: ${sign}`);

      try {
        const response = await axios.get(url, {
          params: {
            from: 'zh',
            to: 'en',
            appid: appId,
            salt,
            sign,
            q: encodeURIComponent(query)// URL 编码待翻译文本

          }
        });
        console.log('Translation response:', response.data);
        return response.data.trans_result[0].dst; // 返回翻译后的结果
      } catch (error) {
        console.error('翻译失败:', error);
        return chineseName; // 如果翻译失败，返回原始城市名
      }
    }
  }
};
</script>

<style scoped>
/* 这里可以添加组件样式 */
</style>
