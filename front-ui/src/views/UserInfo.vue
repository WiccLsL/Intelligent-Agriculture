<template>
  <div>
    <el-form :model="userInfo" :rules="userInfoRules" ref="userInfoForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="userInfo.username" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="userInfo.password"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="userInfo.email"></el-input>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="userInfo.phone"></el-input>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="userInfo.address"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateUserInfo">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'UserInfo',
  data() {
    return {
      userInfo: {
        username: '',
        password: '',
        email: '',
        phone: '',
        address: ''
      },
      userInfoRules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] }
        ],
        phone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    this.loadUserInfo();
  },
  methods: {
    loadUserInfo() {
      fetch('http://localhost:9090/user/info', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      })
          .then(response => response.json())
          .then(data => {
            if (data.success) {
              this.userInfo = data.userInfo;
            } else {
              this.$message.error(data.message || '加载用户信息失败');
            }
          })
          .catch(error => {
            console.error('加载用户信息失败:', error);
            this.$message.error('加载用户信息失败，请稍后重试');
          });
    },
    updateUserInfo() {
      this.$refs.userInfoForm.validate((valid) => {
        if (valid) {
          fetch('http://localhost:9090/user/update', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.userInfo)
          })
              .then(response => response.json())
              .then(data => {
                if (data.success) {
                  this.$message.success('用户信息更新成功');
                } else {
                  this.$message.error(data.message || '用户信息更新失败');
                }
              })
              .catch(error => {
                console.error('用户信息更新失败:', error);
                this.$message.error('用户信息更新失败，请稍后重试');
              });
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style scoped>
/* 添加样式 */
</style>
