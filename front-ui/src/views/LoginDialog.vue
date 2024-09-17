<template>
  <el-dialog
      title="登录"
      :visible.sync="visible"
      width="30%">
    <el-form>
      <el-form-item label="用户名">
        <el-input v-model="loginForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="loginForm.password"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <div class="flex-container">
        <el-link @click="showRegisterForm" class="register-link">注册</el-link>
      </div>
      <div class="flex-container">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="login">登录</el-button>
      </div>
    </div>
  </el-dialog>
</template>

<script>
export default {
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    loginForm: {
      type: Object,
      required: true
    }
  },
  methods: {
    showRegisterForm() {
      // 触发显示注册表单事件
      this.$emit('show-register');
    },
    login() {
      // 触发登录事件
      this.$emit('login');
    }
  }
};
</script>

<style scoped>
/* 样式 */
</style>
