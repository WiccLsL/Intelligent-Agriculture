<template>
  <div>
    <el-table :data="crops" border>
      <el-table-column prop="cropName" label="农作物名称"></el-table-column>
      <el-table-column prop="plantingDate" label="种植时间"></el-table-column>
      <el-table-column prop="expectedHarvestDate" label="预期成熟时间"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="editCrop(scope.row)" size="small">修改</el-button>
          <el-button @click="confirmDelete(scope.row.id)" size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button @click="addPlan" type="primary">添加种植计划</el-button>
    <router-link :to="{ path: '/planting-plan' }">查看所有种植计划</router-link>
  </div>
</template>

<script>
import { MessageBox } from 'element-ui';

export default {
  data() {
    return {
      crops: [] // 存储农作物信息
    };
  },
  mounted() {
    this.fetchCrops();
  },
  methods: {
    fetchCrops() {
      const userId = localStorage.getItem('userId'); // 从本地存储获取用户ID
      console.log('用户ID:', userId);
      if (!userId) {
        console.error('用户ID未找到');
        return;
      }
      fetch(`http://localhost:9090/api/crops/user/${userId}`, {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}` // 如果需要身份验证
        }
      })
          .then(response => {
            if (!response.ok) {
              throw new Error('网络响应不是 OK');
            }
            return response.json();
          })
          .then(data => {
            // 确保 data 是数组并且将其赋值给 crops
            if (Array.isArray(data)) {
              this.crops = data;
            } else {
              console.error('返回的数据格式不正确:', data);
            }
          })
          .catch(error => {
            console.error('获取农作物信息失败:', error);
          });
    },
    confirmDelete(cropId) {
      MessageBox.confirm('此操作将永久删除该农作物, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteCrop(cropId); // 用户确认后执行删除操作
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    deleteCrop(cropId) {
      fetch(`http://localhost:9090/api/crops/${cropId}`, {
        method: 'DELETE',
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}` // 如果需要身份验证
        }
      })
          .then(response => {
            if (response.ok) {
              this.$message({
                type: 'success',
                message: '删除成功'
              });
              this.fetchCrops(); // 刷新列表
            } else {
              this.$message.error('删除农作物失败');
              console.error('删除农作物失败:', response.statusText);
            }
          })
          .catch(error => {
            this.$message.error('删除农作物失败');
            console.error('删除农作物失败:', error);
          });
    }
  }
};
</script>
