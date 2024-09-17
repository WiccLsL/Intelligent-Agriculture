<template>
  <div>
    <h2>种植计划</h2>
    <el-table :data="plans" border>
      <el-table-column prop="cropName" label="农作物名称"></el-table-column>
      <el-table-column prop="planDate" label="计划日期"></el-table-column>
      <el-table-column prop="details" label="计划详情"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="editPlan(scope.row)" size="small">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      plans: [] // 存储种植计划
    };
  },
  mounted() {
    this.fetchPlans();
  },
  methods: {
    fetchPlans() {
      fetch('http://localhost:9090/user/planting-plans', { // 替换为你的 API 地址
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}` // 如果需要身份验证
        }
      })
          .then(response => response.json())
          .then(data => {
            this.plans = data;
          })
          .catch(error => {
            console.error('获取种植计划失败:', error);
          });
    },
    editPlan(plan) {
      // 处理编辑种植计划
      console.log('编辑种植计划:', plan);
    }
  }
};
</script>
