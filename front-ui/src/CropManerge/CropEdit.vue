<template>
  <div class="crop-edit-page">
    <el-form :model="crop" ref="form" label-width="120px" class="crop-info">
      <div class="form-row">
        <el-form-item label="农作物名称" class="form-item">
          <el-input v-model="crop.cropName" placeholder="请输入农作物名称"></el-input>
        </el-form-item>
        <el-form-item label="种植时间" class="form-item">
          <el-date-picker v-model="crop.plantingDate" type="date" placeholder="选择种植时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="预期成熟时间" class="form-item">
          <el-date-picker v-model="crop.expectedHarvestDate" type="date" placeholder="选择预期成熟时间"></el-date-picker>
        </el-form-item>
        <el-form-item class="form-item">
          <el-button type="primary" @click="save">保存农作物信息</el-button>
        </el-form-item>
        <el-form-item class="form-item">
          <el-button @click="$router.go(-1)">返回</el-button>        </el-form-item>
      </div>

    </el-form>

    <div class="plans-reminders">
      <div class="fertilizer-plans">
        <el-table :data="fertilizerPlans" style="width: 100%;">
          <el-table-column prop="frequency" label="施肥频率"></el-table-column>
          <el-table-column prop="time" label="施肥时间"></el-table-column>
          <el-table-column prop="conditions" label="施肥条件"></el-table-column>
          <el-table-column label="操作">
            <template v-slot="scope">
              <div class="table-actions">
                <el-button @click="editFertilizerPlan(scope.row)">修改</el-button>
                <el-button @click="deleteFertilizerPlan(scope.row.id)">删除</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
        <div class="button-center">
          <el-button type="primary" @click="isAddFertilizerPlanDialogVisible = true">添加施肥计划</el-button>
        </div>
      </div>

      <div class="reminders">
        <el-table :data="reminders" style="width: 100%;">
          <el-table-column prop="reminderMessage" label="提醒内容"></el-table-column>
          <el-table-column prop="reminderDate" label="提醒时间"></el-table-column>
          <el-table-column prop="status" label="状态"></el-table-column>
          <el-table-column label="操作">
            <template v-slot="scope">
              <div class="table-actions">
                <el-button @click="editReminder(scope.row)">修改</el-button>
                <el-button @click="deleteReminder(scope.row.id)">删除</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
        <div class="button-center">
          <el-button type="primary" @click="isAddReminderDialogVisible = true">添加提醒</el-button>        </div>

      </div>

    </div>

    <!-- 添加和修改的弹窗 -->
    <el-dialog title="修改施肥计划" :visible.sync="isFertilizerPlanDialogVisible">
      <el-form :model="currentFertilizerPlan">
        <el-form-item label="施肥频率">
          <el-input v-model="currentFertilizerPlan.frequency"></el-input>
        </el-form-item>
        <el-form-item label="施肥时间">
          <el-time-picker v-model="currentFertilizerPlan.time" format="HH:mm"></el-time-picker>
        </el-form-item>
        <el-form-item label="施肥条件">
          <el-input v-model="currentFertilizerPlan.conditions"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="isFertilizerPlanDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateFertilizerPlan">确认</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改提醒" :visible.sync="isReminderDialogVisible">
      <el-form :model="currentReminder">
        <el-form-item label="提醒内容">
          <el-input v-model="currentReminder.reminderMessage"></el-input>
        </el-form-item>
        <el-form-item label="提醒时间">
          <el-date-picker v-model="currentReminder.reminderDate" type="datetime"></el-date-picker>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="currentReminder.status">
            <el-option label="已完成" value="completed"></el-option>
            <el-option label="未完成" value="pending"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="isReminderDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateReminder">确认</el-button>
      </span>
    </el-dialog>

    <el-dialog title="添加提醒" :visible.sync="isAddReminderDialogVisible">
      <el-form :model="newReminder">
        <el-form-item label="提醒内容">
          <el-input v-model="newReminder.reminderMessage"></el-input>
        </el-form-item>
        <el-form-item label="提醒时间">
          <el-date-picker v-model="newReminder.reminderDate" type="datetime"></el-date-picker>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="newReminder.status">
            <el-option label="已完成" value="completed"></el-option>
            <el-option label="未完成" value="pending"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="isAddReminderDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addReminder">确认</el-button>
      </span>
    </el-dialog>

    <el-dialog title="添加施肥计划" :visible.sync="isAddFertilizerPlanDialogVisible">
      <el-form :model="newFertilizerPlan">
        <el-form-item label="施肥频率">
          <el-input v-model="newFertilizerPlan.frequency"></el-input>
        </el-form-item>
        <el-form-item label="施肥时间">
          <el-time-picker v-model="newFertilizerPlan.time" format="HH:mm"></el-time-picker>
        </el-form-item>
        <el-form-item label="施肥条件">
          <el-input v-model="newFertilizerPlan.conditions"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="isAddFertilizerPlanDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addFertilizerPlan">确认</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios';
import moment from 'moment';
import './CropEdit.css'; // 引入 CSS 文件

export default {
  props: ['id'],
  data() {
    return {
      crop: {
        cropName: '',
        plantingDate: '',
        expectedHarvestDate: ''
      },
      fertilizerPlans: [],
      reminders: [],
      isFertilizerPlanDialogVisible: false,
      isReminderDialogVisible: false,
      currentFertilizerPlan: {},
      currentReminder: {},
      newFertilizerPlan: {
        frequency: '',
        time: '',
        conditions: ''
      },
      newReminder: {
        reminderMessage: '',
        reminderDate: '',
        status: 'pending'
      },
      isAddFertilizerPlanDialogVisible: false,
      isAddReminderDialogVisible: false
    };
  },
  created() {
    this.loadCropData();
    this.loadFertilizerPlans();
    this.loadReminders();
  },
  methods: {
    loadCropData() {
      axios.get(`http://localhost:9090/api/crops/${this.id}`)
          .then(response => {
            this.crop = response.data;
          })
          .catch(error => {
            console.error("获取农作物信息失败", error);
          });
    },
    loadFertilizerPlans() {
      axios.get(`http://localhost:9090/api/fertilizer-watering-plans/${this.id}`)
          .then(response => {
            this.fertilizerPlans = response.data;
          })
          .catch(error => {
            console.error("获取施肥计划失败", error);
          });
    },
    loadReminders() {
      axios.get(`http://localhost:9090/api/reminders/${this.id}`)
          .then(response => {
            this.reminders = response.data;
          })
          .catch(error => {
            console.error("获取提醒信息失败", error);
          });
    },
    deleteFertilizerPlan(id) {
      axios.delete(`http://localhost:9090/api/fertilizer-watering-plans/${id}`)
          .then(() => {
            this.loadFertilizerPlans();
            this.$message.success('施肥计划删除成功');
          })
          .catch(error => {
            console.error("删除施肥计划失败", error);
          });
    },
    editFertilizerPlan(plan) {
      this.currentFertilizerPlan = { ...plan };
      this.isFertilizerPlanDialogVisible = true;
    },
    updateFertilizerPlan() {
      // 格式化时间为 HH:mm
      const formattedTime = moment(this.currentFertilizerPlan.time).format('HH:mm');
      axios.put(`http://localhost:9090/api/fertilizer-watering-plans/${this.currentFertilizerPlan.id}`, {...this.currentFertilizerPlan,
        cropId: this.id, time: formattedTime })
          .then(() => {
            this.loadFertilizerPlans();
            this.isFertilizerPlanDialogVisible = false;
            this.$message.success('施肥计划修改成功');
          })
          .catch(error => {
            console.error("修改施肥计划失败", error);
          });
    },
    addFertilizerPlan() {
      // 格式化时间为 HH:mm
      const formattedTime = moment(this.newFertilizerPlan.time).format('HH:mm');

      // 发送 POST 请求
      axios.post(`http://localhost:9090/api/fertilizer-watering-plans`, {
        ...this.newFertilizerPlan,
        cropId: this.id,
        time: formattedTime // 使用格式化后的时间
      })
          .then(() => {
            this.loadFertilizerPlans(); // 刷新施肥计划列表
            console.log(this.newFertilizerPlan);
            this.isAddFertilizerPlanDialogVisible = false; // 关闭对话框
            this.$message.success('施肥计划添加成功'); // 成功提示
          })
          .catch(error => {
            console.error("添加施肥计划失败", error); // 错误提示
          });
    },


    deleteReminder(id) {
      axios.delete(`http://localhost:9090/api/reminders/${id}`)
          .then(() => {
            this.loadReminders();
            this.$message.success('提醒删除成功');
          })
          .catch(error => {
            console.error("删除提醒失败", error);
          });
    },
    editReminder(reminder) {
      this.currentReminder = { ...reminder };
      this.isReminderDialogVisible = true;
    },
    updateReminder() {
      axios.put(`http://localhost:9090/api/reminders/${this.currentReminder.id}`, this.currentReminder)
          .then(() => {
            this.loadReminders();
            this.isReminderDialogVisible = false;
            this.$message.success('提醒修改成功');
          })
          .catch(error => {
            console.error("修改提醒失败", error);
          });
    },
    addReminder() {
      axios.post(`http://localhost:9090/api/reminders`, { ...this.newReminder, cropId: this.id })
          .then(() => {
            this.loadReminders();
            this.isAddReminderDialogVisible = false;
            this.$message.success('提醒添加成功');
          })
          .catch(error => {
            console.error("添加提醒失败", error);
          });
    },
    save() {
      // 保存农作物信息的逻辑
      console.log('保存农作物信息', this.crop);
      this.$message.success('保存农作物信息成功');

    }
  }
}
</script>

<style scoped>
.crop-edit-page {
  padding: 20px;
}

.crop-info {
  margin-bottom: 20px;
}

.button-center {
  text-align: center;
  margin: 20px 0;
}

.plans-reminders {
  display: flex;
  justify-content: space-between;
}

.fertilizer-plans, .reminders {
  width: 48%;
}

.table-actions {
  display: flex;
  justify-content: space-between;
}
</style>
