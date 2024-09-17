<template>
  <div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
      <el-table-column prop="id" label="ID" width="140"></el-table-column>
      <el-table-column prop="username" label="用户名" width="140"></el-table-column>
      <el-table-column prop="nickname" label="姓名" width="140"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="120"></el-table-column>
      <el-table-column prop="phone" label="电话" width="120"></el-table-column>
      <el-table-column prop="address" label="业务"></el-table-column>
    </el-table>
    <div class="block" style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserInfo2',
  data() {
    return {
      tableData: [],
      total: 0,
      currentPage4: 0,
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      pageNum: 1,
      pageSize: 15,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg'
    };
  },
  created() {
    this.loadData();
  },
  methods: {
    created() {
      //请求分页查询数据
      this.loadData();
    },
    // 加载数据方法
    loadData() {
      fetch(`http://localhost:9090/user/page?pageNum=${this.pageNum}&pageSize=${this.pageSize}`)
          .then(res => res.json())
          .then(res => {
            console.log(res);
            this.tableData = res.data;
            this.total = res.total;
          })
          .catch(error => {
            console.error('加载数据失败:', error);
            // 显示错误消息给用户
            this.$message.error('加载数据失败，请稍后重试');
          });

    },

    // 处理每页显示条数改变事件
    handleSizeChange(pageSize) {
      console.log('每页条数改变为:', pageSize);
      this.pageSize = pageSize;
      this.loadData();
    },

    // 处理当前页码改变事件
    handleCurrentChange(pageNum) {
      console.log('当前页码改变为:', pageNum);
      this.pageNum = pageNum;
      this.loadData();
    }
  }
};
</script>

<style scoped>
@import './UserInfo2Styles.css';
</style>
