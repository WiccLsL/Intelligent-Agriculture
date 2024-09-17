<template>
  <div class="latest-news-container">
    <h1>最新农业新闻与知识科普</h1>
    <div class="content">
      <div class="news-section">
        <h2>最新农业新闻</h2>
        <div class="news-item" v-for="news in newsList" :key="news.id">
          <el-card shadow="never">
            <h3>{{ news.title }}</h3>
            <p>{{ news.content }}</p>
            <span>{{ news.date }}</span>
          </el-card>
        </div>
      </div>
      <div class="knowledge-section">
        <h2>农业知识科普</h2>
        <div class="knowledge-item" v-for="knowledge in knowledgeList" :key="knowledge.id">
          <el-card shadow="never">
            <h3>{{ knowledge.title }}</h3>
            <p>{{ knowledge.content }}</p>
          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LatestNews',
  data() {
    return {
      newsList: [
        {
          id: 1,
          title: '农业科技的最新进展',
          content: '近期，农业科技领域取得了新的突破，这将大大提高农作物的产量和质量。',
          date: '2024-05-16',
        },
        {
          id: 2,
          title: '有机农业的益处',
          content: '有机农业不仅有助于环境保护，还能提供更健康的农产品。',
          date: '2024-05-15',
        },
        {
          id: 3,
          title: '现代农业机械的发展',
          content: '现代农业机械的发展使得农业生产变得更加高效和便捷。',
          date: '2024-05-14',
        },
      ],
      knowledgeList: [
        {
          id: 1,
          title: '土壤养分管理',
          content: '土壤养分管理是现代农业生产中的重要环节，通过合理的施肥和轮作，可以提高土壤肥力，增加作物产量。',
        },
        {
          id: 2,
          title: '病虫害防治',
          content: '病虫害防治是农业生产中的重要组成部分，通过综合管理措施，可以有效减少农药使用，保护环境。',
        },
        {
          id: 3,
          title: '智能农业技术',
          content: '智能农业技术包括精准农业、农业物联网等，通过数据分析和自动化技术，实现农业生产的智能化和高效化。',
        },
      ],
    };
  },
};
</script>

<style scoped>
.latest-news-container {
  max-width: 1500px;
  margin:  auto;
  margin-top: 50px;
  height: 80vh;
  padding: 30px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: #333;
  font-size: 2.5em;
  margin-bottom: 20px;
}

.content {
  display: flex;
  justify-content: space-between;
  gap: 20px;
}

.news-section, .knowledge-section {
  flex: 1;
}

.news-item, .knowledge-item {
  margin-bottom: 20px;
}

.news-item h3, .knowledge-item h3 {
  font-size: 1.5em;
  color: #333;
}

.news-item p, .knowledge-item p {
  font-size: 1em;
  color: #666;
  line-height: 1.6;
}

.news-item span {
  font-size: 0.9em;
  color: #999;
}
</style>
