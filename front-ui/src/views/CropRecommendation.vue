<template>
  <div class="container">
    <div class="hero">
      <h1 class="hero-title">作物推荐系统</h1>
      <p class="hero-subtitle">根据土壤和天气条件获取最佳作物推荐</p>
    </div>

    <div class="form-section">
      <h2>作物推荐</h2>
      <form @submit.prevent="getRecommendation" class="form">
        <div class="form-group">
          <label for="N">氮含量 (N):</label>
          <input type="number" id="N" v-model.number="inputData.N" required />
        </div>
        <div class="form-group">
          <label for="P">磷含量 (P):</label>
          <input type="number" id="P" v-model.number="inputData.P" required />
        </div>
        <div class="form-group">
          <label for="K">钾含量 (K):</label>
          <input type="number" id="K" v-model.number="inputData.K" required />
        </div>
        <div class="form-group">
          <label for="temperature">温度:</label>
          <input type="number" id="temperature" v-model.number="inputData.temperature" step="0.1" required />
        </div>
        <div class="form-group">
          <label for="humidity">湿度:</label>
          <input type="number" id="humidity" v-model.number="inputData.humidity" required />
        </div>
        <div class="form-group">
          <label for="ph">pH 值:</label>
          <input type="number" id="ph" v-model.number="inputData.ph" step="0.1" required />
        </div>
        <div class="form-group">
          <label for="rainfall">降雨量:</label>
          <input type="number" id="rainfall" v-model.number="inputData.rainfall" required />
        </div>
        <button type="submit" class="submit-btn">获取推荐</button>
      </form>

      <div v-if="recommendation" class="recommendation">
        <h3>推荐的作物:</h3>
        <p>{{ recommendation }}</p>
      </div>
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

<<style scoped>
.container {
  max-width: 1800px;
  margin: 100px auto;

  padding: 20px;
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.hero {
  background-image:linear-gradient(60deg,#abecd6 0%, #fbed96 100%) ;
  padding: 40px;
  border-radius: 12px;
  text-align: center;
  margin-bottom: 30px;
}

.hero-title {
  font-size: 2.5rem;
  margin: 0;
}

.hero-subtitle {
  font-size: 1.25rem;
  margin-top: 10px;
}

.form-section,
.weather-section {
  margin-bottom: 40px;
}

h2 {
  color: #333;
  margin-bottom: 20px;
  font-size: 2rem;
  font-weight: bold;
}

.form {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group label {
  margin-bottom: 8px;
  font-weight: bold;
  color: #555;
}

.form-group input {
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 16px;
}

.submit-btn {
  grid-column: span 2;
  padding: 15px 25px;
  background-image:linear-gradient(60deg,#64b3f4 0%, #c2e59c 100%);
  color: #fff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 18px;
  transition: background-color 0.3s, transform 0.2s;
}

.submit-btn:hover {
  background-color: #0056b3;
}

.submit-btn:active {
  transform: scale(0.98);
}

.recommendation {
  padding: 20px;
  background-color: #e9f5f7;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.weather-form {
  display: flex;
  justify-content: center;
  gap: 15px;
  margin-bottom: 20px;
}

.weather-form input {
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 16px;
}

.weather-btn {
  padding: 15px 25px;
  background-image:linear-gradient(60deg,#64b3f4 0%, #c2e59c 100%);  color: #fff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 18px;
  transition: background-color 0.3s, transform 0.2s;
}

.weather-btn:hover {
  background-image:linear-gradient(60deg,#64b3f4 0%, #c2e59c 100%);}

.weather-btn:active {
  transform: scale(0.98);
}

.weather-data {
  padding: 20px;
  background-color: #f4f4f4;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.weather-data p {
  margin: 10px 0;
  font-size: 16px;
}
</style>
