<template>
  <div>
    <el-table :data="crops" border>
      <el-table-column prop="cropName" label="农作物名称"></el-table-column>
      <el-table-column prop="plantingDate" label="种植时间"></el-table-column>
      <el-table-column prop="expectedHarvestDate" label="预期成熟时间"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="goToEditPage(scope.row.id)" size="small">管理</el-button>          <el-button @click="confirmDelete(scope.row.id)" size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button @click="openAddPlanDialog" type="primary">添加种植计划</el-button>

    <!-- 添加种植计划对话框 -->
    <el-dialog title="添加种植计划" :visible.sync="dialogVisible" width="30%">
      <el-form :model="newCrop" ref="form" label-width="120px">
        <el-form-item label="农作物名称" prop="cropName" :rules="[{ required: true, message: '请输入农作物名称', trigger: 'blur' }]">
          <el-input v-model="newCrop.cropName"></el-input>
        </el-form-item>
        <el-form-item label="种植时间" prop="plantingDate" :rules="[{ required: true, message: '请选择种植时间', trigger: 'change' }]">
          <el-date-picker v-model="newCrop.plantingDate" type="date" placeholder="选择种植时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="预期成熟时间" prop="expectedHarvestDate" :rules="[{ required: true, message: '请选择预期成熟时间', trigger: 'change' }]">
          <el-date-picker v-model="newCrop.expectedHarvestDate" type="date" placeholder="选择预期成熟时间"></el-date-picker>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCrop">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { MessageBox } from 'element-ui';

export default {
  data() {
    return {
      crops: [], // 存储农作物信息
      dialogVisible: false, // 控制对话框显示
      newCrop: { // 新作物信息
        cropName: '',
        plantingDate: '',
        expectedHarvestDate: ''
      }
    };
  },
  mounted() {
    this.fetchCrops();
  },
  methods: {
    goToEditPage(id) {
      this.$router.push({ name: 'CropEdit', params: { id } });
    },
    fetchCrops() {
      const userId = localStorage.getItem('userId'); // 从本地存储获取用户ID
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
    openAddPlanDialog() {
      this.dialogVisible = true; // 显示对话框
    },
    addCrop() {
      this.$refs.form.validate(valid => {
        if (valid) {
          fetch('http://localhost:9090/api/crops', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            body: JSON.stringify(this.newCrop)
          })
              .then(response => {
                if (!response.ok) {
                  throw new Error('网络响应不是 OK');
                }
                return response.json(); // 解析 JSON 响应
              })
              .then(data => {
                // 假设返回的 JSON 对象中包含新创建作物的 ID
                const cropId = data.id; // 获取新作物的 ID
                if (!cropId) {
                  console.error('农作物ID未找到');
                  return;
                }
                // 将作物ID存储到本地存储中
                localStorage.setItem('cropId', cropId);

                const userId = localStorage.getItem('userId');
                return fetch('http://localhost:9090/api/userCrops', {
                  method: 'POST',
                  headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                  },
                  body: JSON.stringify({ userId, cropId })
                });
              })
              .then(response => {
                if (response.ok) {
                  this.$message({
                    type: 'success',
                    message: '作物添加成功'
                  });
                  this.dialogVisible = false;
                  this.fetchCrops(); // 刷新列表
                } else {
                  throw new Error('添加用户作物关系失败');
                }
              })
              .catch(error => {
                this.$message.error('添加作物失败');
                console.error('添加作物失败:', error);
              });
        }
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

<style scoped>
/* 添加样式（如果需要） */
</style>
