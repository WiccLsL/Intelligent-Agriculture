<template>
  <div class="recommendation-container">
    <h1 class="title">个性化农作物推荐</h1>
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item class="form-item-region" label="地区">
        <el-input v-model="form.region" placeholder="请输入地区" class="styled-input"></el-input>
      </el-form-item>
      <el-form-item class="form-item-button">
        <el-button type="primary" @click="getRecommendation" class="styled-button">获取推荐</el-button>
      </el-form-item>
    </el-form>
    <div v-if="recommendations.length" class="recommendation-results">
      <h2>推荐结果</h2>
      <div v-for="(recommendation, index) in recommendations" :key="index" class="recommendation">
        <h3>{{ recommendation.crop }}</h3>
        <pre>{{ recommendation.plan }}</pre>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CropRecommendation',
  data() {
    return {
      form: {
        region: ''
      },
      recommendations: []
    };
  },
  methods: {
    getRecommendation() {
      fetch(`http://localhost:9090/api/recommendations?region=${this.form.region}`)
          .then(response => response.json())
          .then(data => {
            if (data.success) {
              this.recommendations = data.recommendations;
            } else {
              this.$message.error('获取推荐失败');
            }
          })
          .catch(error => {
            console.error('获取推荐失败:', error);
            this.$message.error('获取推荐失败');
          });
    }
  }
};
</script>

<style scoped>
.recommendation-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  height: 100vh;
  width: 100%;
  padding: 20px;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  overflow: auto;
}

.title {
  font-size: 36px;
  font-weight: bold;
  color: #409EFF;
  margin-bottom: 20px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
}

.demo-form-inline {
  margin-bottom: 20px;
  display: flex;
  justify-content: center;
}

.form-item-region {
  margin-right: 10px;
}

.styled-input .el-input__inner {
  width: 300px;
  border-radius: 5px;
  padding: 10px;
  font-size: 16px;
  background-color: #ffffff;
  border: 2px solid #409EFF;
}

.form-item-region .el-form-item__label {
  font-size: 18px; /* 设置字体大小 */
  color: #333;
  font-weight: bold; /* 设置字体粗细 */
  margin-right: 10px;
}

.styled-button {
  background-color: #409EFF;
  color: white;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 16px;
  font-weight: bold;
  transition: background-color 0.3s, transform 0.3s;
}

.styled-button:hover {
  background-color: #66b1ff;
  transform: translateY(-2px);
}

.recommendation-results {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 80%;
  font-size: 16px;
  margin-top: 20px;
}

.recommendation {
  margin-bottom: 20px;
}

.recommendation h3 {
  color: #409EFF;
  font-weight: bold;
}

.recommendation pre {
  background-color: #f2f4f5;
  padding: 10px;
  border-radius: 5px;
  white-space: pre-wrap;
  word-wrap: break-word;
}
</style>
