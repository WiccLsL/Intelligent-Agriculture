<template>
  <div class="declaration-container">
    <h2>声明</h2>
    <h3>联系页展示用户个人信息持续更新中···</h3>
    <h4>按需寻找合作用户（电话/邮箱）见联系页</h4>
    <h4>遇到问题请联系客服（电话/邮箱）见联系页</h4>
  </div>
</template>

<script>
export default {
  name: 'Declaration'
};
</script>

<style scoped>
.declaration-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.declaration-container h2 {
  font-size: 2rem;
  color: #333;
  margin-bottom: 20px;
  text-align: center;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
}

.declaration-container h3 {
  font-size: 1.5rem;
  color: #555;
  margin-bottom: 15px;
}

.declaration-container h4 {
  font-size: 1.2rem;
  color: #666;
  margin-bottom: 10px;
}
</style>
