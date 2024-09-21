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
    <!-- 登录弹出框 -->
    <el-dialog
        title="登录"
        :visible.sync="loginDialogVisible"
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
          <el-button @click="loginDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="login">登录</el-button>
        </div>
      </div>
    </el-dialog>
    <!-- 注册视图组件 -->
    <RegisterView v-if="showRegisterView" @close="handleRegisterClose" />
    <el-container style="height: 100vh;">

      <el-aside height="900px" width="188px" style="background-color: #f0f0f0; border-radius: 30px;" class="menu-bar">
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            background-color="#f0f0f0"
            text-color="black"

            active-text-color="#ffd04b">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>Welcome！</span>
            </template>
            <el-menu-item-group>
              <template slot="title">Function</template>
              <el-menu-item index="1-1"><router-link to="/CropRecommendation" style="display: flex; align-items: center; color: inherit; text-decoration: none;">个性化种植</router-link></el-menu-item>
              <el-menu-item index="1-2">气象预测</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="OvO">
              <el-menu-item index="1-3"><router-link to="/gao-de" style="display: flex; align-items: center; color: inherit; text-decoration: none;">提醒事项</router-link></el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">合作/帮助</template>
              <el-menu-item index="1-4-1">
                <router-link to="/user-info2" style="display: flex; align-items: center; color: inherit; text-decoration: none;">
                  <span>联系</span>
                </router-link>
              </el-menu-item>
              <el-menu-item index="1-4-2">
                <router-link to="/declaration" style="display: flex; align-items: center; color: inherit; text-decoration: none;">声明</router-link>
              </el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">
    <router-link to="/crop-management" style="color: inherit; text-decoration: none;">农作物管理</router-link>
  </span>
          </el-menu-item>

          <!--          <el-menu-item index="3">-->
<!--            <router-link to="/userinfo" style="display: flex; align-items: center; color: inherit; text-decoration: none;">-->
<!--              <i class="el-icon-document"></i>-->
<!--              <span>我的信息</span>-->
<!--            </router-link>-->
<!--          </el-menu-item>-->
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">设置</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="text-align: left; font-size: 12px; solid: #ccc;">
          <div style="display: flex; align-items: center; margin-top: 12px">
            <img src="../../public/hhhh.png" height="70" width="275"/>
          </div>
          <!-- 下拉按钮 -->
          <el-dropdown style="float: right; margin-top: -40px;">
            <i class="el-icon-user" @click="showLoginForm" style="margin-right: 20px; font-size: 20px;"></i>
            <i class="el-icon-caret-bottom" style="margin-right: 30px; font-size: 20px;"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item  @click="showLoginForm">登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>
        <el-main style="height: 100vh;">
          <div id="map" style="height: 100%;border-radius: 30px;" ></div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// 导入注册视图组件
import RegisterView from './RegisterView.vue';
export default {
  name: 'HomeView',
  components: {
    RegisterView // 注册为 HomeView 的子组件
  },
  data() {
    return {
      msg: "Hello,智慧农业",
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      map: null,
      avatarUrl: 'https://example.com/avatar.jpg',// 这里替换成你的头像 URL
      headerBg: 'headerBg',
      loginDialogVisible: false, // 控制登录弹窗显示与隐藏的变量
      isLoggedIn: false,  // 新增属性，记录用户登录状态
      showRegisterView: false,
      loginForm: { // 登录表单数据对象
        username: '',
        password: ''
      },
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
  mounted() {
    // 在 mounted 钩子中初始化地图
    this.initMap();
  },
  methods: {
    // 初始化地图
    initMap() {
      const map = new AMap.Map('map', {
        center: [116.486409, 39.921489],
        zoom: 12
      });
      map.setMapStyle('amap://styles/light');
    },
    showLoginForm() {
      console.log("showLoginForm called");
      this.loginDialogVisible = true;
      console.log(this.loginDialogVisible);
    },
    showRegisterForm() {
      // 显示注册弹窗
      this.registerDialogVisible = true;
    },
    handleRegisterClose() {
      // 处理注册弹窗关闭
      this.showRegisterView = false;
    },
    // 处理登录方法
    login() {
      if (this.isLoggedIn) {
        // 如果用户已经登录，则不允许重新登录
        this.$message.info('您已经登录，无需再次登录');
        return;}
      // 构造登录请求数据
      const requestData = {
        username: this.loginForm.username,
        password: this.loginForm.password
      };

      // 发起登录请求
      fetch('http://localhost:9090/user/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
      })
          .then(response => {
            if (!response.ok) {
              // 如果响应不是 200 OK，则抛出异常，进入下面的 catch 块
              throw new Error('Network response was not ok');
            }
            // 返回响应的 JSON 数据
            return response.json();
          })
          .then(data => {
            // 处理登录成功或失败的情况
            if (data != null) {
              this.isLoggedIn = true; // 登录成功后设置状态
              // 登录成功
              this.loginSuccess = true;
              this.loginError = false;
              // 关闭登录弹窗
              this.loginDialogVisible = false;
              // 显示登录成功消息
              this.$message.success('登录成功！');
              // 假设后端返回的用户 ID 在 data.userId
              const userId = data.id;
              console.log('登录成功，用户 :', data);
              console.log('登录成功，用户 ID:', userId);
              localStorage.setItem('userId', userId);  // 保存用户ID到本地存储
            } else {
              // 登录失败
              this.loginSuccess = false;
              this.loginError = true;
              // 显示登录失败消息
              this.$message.error(data); // 这里直接将后端返回的文本作为错误消息显示
            }
          })
          .catch(error => {
            // 处理登录请求失败的情况
            console.error('登录失败:', error);
            this.loginSuccess = false;
            this.loginError = true;
            // 显示错误消息
            this.$message.error('登录失败，请稍后重试');
          });
    },
    howLoginForm() {
      // 显示登录框
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
            body: JSON.stringify(this.registerForm) // 确保包含密码字段
          })
              .then(response => response.json())
              .then(data => {
                if (data.success) {
                  this.$message.success('注册成功');
                  this.registerDialogVisible = false;
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

<style scoped>
/* 这里的路径应该是相对于当前 Vue 文件的 */
@import './HomeViewStyles.css';
</style>