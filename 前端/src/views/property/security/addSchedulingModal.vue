<template>
  <div class='setting-modal'>
    <div class='modal-content'>
      <div class='modal-header'>{{ state ? '修改' : '新增'}}排班</div>
      <div class="modal-form">
        <div class="modal-upload">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="姓名">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="部门">
              <el-input v-model="form.org"></el-input>
            </el-form-item>
            <el-form-item label="岗位">
              <el-input v-model="form.work"></el-input>
            </el-form-item>
            <el-form-item label="开始时间">
              <el-date-picker
                      style="width: 100%"
                      v-model="form.workTime"
                      format="yyyy-MM-dd"
                      value-format="yyyy-MM-dd"
                      type="date"
                      placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间">
              <el-date-picker
                      style="width: 100%"
                      v-model="form.endTime"
                      format="yyyy-MM-dd"
                      value-format="yyyy-MM-dd"
                      type="date"
                      placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="工作地点">
              <el-input v-model="form.address"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div class="modal-btn">
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="close">取消</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "addUserModal",
    data() {
      return {
        form:{
          org:'',
          name:'',
          address:'',
          work:'',
          workTime:'',
          endTime: ''
        },
      }
    },
    props: [ 'state', 'row' ],
    mounted() {
      if (this.state === 1) {
        this.form = this.row
      }
    },
    methods: {
      onSubmit(){
        if (this.state === 0) {
          this.$http.post('/user/cleanAdd',this.form)
            .then((res) => {
              if (res.data.status === 200) {
                this.$message.success('恭喜你，添加成功')
                this.close()
              } else {
                this.$message.error(res.data.msg)
              }
            })
        } else {
          this.$http.post('/user/cleanUpdate',this.form)
            .then((res) => {
              if (res.data.status === 200) {
                this.$message.success('恭喜你，修改成功')
                this.close()
              } else {
                this.$message.error(res.data.msg)
              }
            })
        }
      },
      close(){
        console.log('关闭')
        this.$emit('showAddModal', false)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .setting-modal {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 102;
    background-color:rgba(255,255,255,0.6);
    .modal-content {
      width: 600px;
      background: rgba(255, 255, 255, 1);
      box-shadow: 8px 8px 29px 0px rgba(7, 42, 90, 0.7);
      border-radius: 3px;
      z-index: 101;
      color: rgba(59, 86, 129, 1);
      position: relative;
      
      .modal-header {
        height: 65px;
        margin: 0;
        font-size: 24px;
        line-height: 65px;
        text-align: center;
        font-family: PingFang-SC;
        font-weight: 400;
        color: rgba(59, 86, 129, 1);
        border-bottom: 1px solid rgba(235, 236, 240, 0.95);
      }
      .modal-form{
        height: 100%;
        padding: 20px;
        .modal-btn{
          text-align: center;
        }
      }
    }
  }
</style>
