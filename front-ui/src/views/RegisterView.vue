<template>
  <div>
    <!-- 注册弹出框 -->
    <el-dialog
        title="注册"
        :visible.sync="registerDialogVisible"
        width="40%">
      <el-form :model="registerForm" :rules="registerRules" ref="registerForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="registerForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="registerForm.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input type="password" v-model="registerForm.confirmPassword"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="registerForm.email"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <div class="flex-container">
          <el-link @click="showLoginForm" class="login-link">已有账号？去登录</el-link>
        </div>
        <div class="flex-container">
          <el-button @click="registerDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="register">注册</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      registerDialogVisible: false,
      registerForm: {
        username: '',
        password: '',
        confirmPassword: '',
        email: ''
      },
      registerRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              if (value !== this.registerForm.password) {
                callback(new Error('两次输入的密码不一致'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] }
        ]
      }
    };
  },
  methods: {
    showLoginForm() {
      // 显示登录框
      this.loginDialogVisible = true;
    },
    register() {
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          // 发送注册请求
          fetch('http://localhost:9090/user/register', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.registerForm)
          })
              .then(response => response.json())
              .then(data => {
                // 处理注册响应
                if (data.success) {
                  this.$message.success('注册成功');
                  // 关闭注册框
                  this.registerDialogVisible = false;
                  // 可以进行自动登录等操作
                } else {
                  this.$message.error(data.message || '注册失败');
                }
              })
              .catch(error => {
                console.error('注册失败:', error);
                this.$message.error('注册失败，请稍后重试');
              });
        } else {
          return false;
        }
      });
    }
  }
};
</script>
