<template>
  <div class='setting-modal'>
    <div class='modal-content'>
      <div class='modal-header'>新增报修</div>
      <div class="modal-form">
        <div class="modal-upload">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="业主姓名">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="业主电话">
              <el-input v-model="form.phone"></el-input>
            </el-form-item>
            <el-form-item label="报修位置">
              <el-input v-model="form.position"></el-input>
            </el-form-item>
            <el-form-item label="维修人">
              <el-input v-model="form.repair"></el-input>
            </el-form-item>
            <el-form-item label="维修人电话">
              <el-input v-model="form.repairPhone"></el-input>
            </el-form-item>
            <el-form-item label="配件">
              <el-input v-model="form.parts"></el-input>
            </el-form-item>
            <el-form-item label="数量">
              <el-input v-model="form.num"></el-input>
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
    name: "addModal",
    data() {
      return {
        form:{
          name:'',
          phone:'',
          position:'',
          repair:'',
          repairPhone:'',
          parts:'',
          num:''
        },
      }
    },
    methods: {
      onSubmit(){
        this.$message.success('提交成功')
        this.close()
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
