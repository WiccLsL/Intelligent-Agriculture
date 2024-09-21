<template>
  <div>
    <el-form :model="crop" ref="form" label-width="120px">
      <el-form-item label="农作物名称">
        <el-input v-model="crop.cropName" placeholder="请输入农作物名称"></el-input>
      </el-form-item>
      <el-form-item label="种植时间">
        <el-date-picker v-model="crop.plantingDate" type="date" placeholder="选择种植时间"></el-date-picker>
      </el-form-item>
      <el-form-item label="预期成熟时间">
        <el-date-picker v-model="crop.expectedHarvestDate" type="date" placeholder="选择预期成熟时间"></el-date-picker>
      </el-form-item>

      <el-divider>施肥/浇水计划</el-divider>

      <!-- FertilizerWateringPlan 信息 -->
      <el-form-item label="施肥频率">
        <el-input v-model="fertilizerWateringPlan.frequency" placeholder="请输入施肥频率"></el-input>
      </el-form-item>
      <el-form-item label="施肥时间">
        <el-time-picker v-model="fertilizerWateringPlan.time" format="HH:mm" placeholder="选择施肥时间"></el-time-picker>
      </el-form-item>
      <el-form-item label="施肥条件">
        <el-input v-model="fertilizerWateringPlan.conditions" placeholder="请输入施肥条件"></el-input>
      </el-form-item>

      <el-divider>提醒信息</el-divider>

      <!-- Reminder 信息 -->
      <el-form-item label="提醒信息">
        <el-input v-model="reminder.reminderMessage" placeholder="请输入提醒内容"></el-input>
      </el-form-item>
      <el-form-item label="提醒时间">
        <el-date-picker v-model="reminder.reminderDate" type="datetime" placeholder="选择提醒时间"></el-date-picker>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="reminder.status" placeholder="选择状态">
          <el-option label="已完成" value="completed"></el-option>
          <el-option label="未完成" value="pending"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="save">保存</el-button>
        <el-button @click="$router.go(-1)">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';
import moment from'moment';
export default {
  props: ['id'],
  data() {
    return {
      crop: {
        cropName: '',
        plantingDate: '',
        expectedHarvestDate: ''
      },
      fertilizerWateringPlan: {
        frequency: '',
        time: '',
        conditions: ''
      },
      reminder: {
        reminderMessage: '',
        reminderDate: '',
        status: ''
      }
    }
  },
  created() {
    this.loadCropData();
  },
  methods: {
    loadCropData() {
      // 加载农作物信息
      axios.get(`http://localhost:9090/api/crops/${this.id}`)
          .then(response => {
            this.crop = response.data;
            console.log("农作物信息：", this.crop);

          })
          .catch(error => {
            console.error("获取农作物信息失败", error);
          });


      // 加载 FertilizerWateringPlan 信息
      axios.get(`http://localhost:9090/api/fertilizer-watering-plans/${this.id}`)
          .then(response => {
            this.fertilizerWateringPlan = response.data;
            response.data.time = moment(response.data.time, 'HH:mm').format('HH:mm'); // 格式化时间
            console.log("施肥计划：", this.fertilizerWateringPlan); // 打印施肥计划
          })
          .catch(error => {
            console.error("获取施肥计划失败", error);
          });

      // 加载 Reminder 信息
      axios.get(`http://localhost:9090/api/reminders/${this.id}?cropId=${this.cropId}`)
          .then(response => {
            const reminderData = response.data;
            if (Array.isArray(reminderData) && reminderData.length > 0) {
              this.reminder = reminderData[0]; // 取第一个提醒信息
            } else {
              console.error("提醒信息数据格式错误", reminderData);
            }
          })
          .catch(error => {
            console.error("获取提醒信息失败", error);
          });
    },
    save() {
      // 保存农作物信息
      axios.put(`http://localhost:9090/api/crops/${this.id}`, this.crop)
          .then(() => {
            this.$message.success('农作物信息保存成功');
          })
          .catch(error => {
            console.error("保存农作物信息失败", error);
          });

      // 保存 FertilizerWateringPlan 信息
      axios.put(`http://localhost:9090/api/fertilizer-watering-plans/${this.id}`, this.fertilizerWateringPlan)
          .then(() => {
            this.$message.success('施肥计划保存成功');
          })
          .catch(error => {
            console.error("保存施肥计划失败", error);
          });

      // 保存 Reminder 信息
      axios.put(`http://localhost:9090/api/reminders/${this.id}`, this.reminder)
          .then(() => {
            this.$message.success('提醒信息保存成功');
          })
          .catch(error => {
            console.error("保存提醒信息失败", error);
          });
    }
  }
}
</script>
