<template>
  <div class='setting-modal'>
    <div class='modal-content'>
      <div class='modal-header'>修改密码</div>
      <div class="modal-form">
        <div class="modal-upload">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="当前密码">
              <el-input v-model="form.pwd"></el-input>
            </el-form-item>
            <el-form-item label="新密码">
              <el-input v-model="form.newPwd"></el-input>
            </el-form-item>
            <el-form-item label="确认密码">
              <el-input v-model="confirmPwd"></el-input>
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
    name: "password",
    data() {
      return {
        form:{
          code:sessionStorage.getItem('code'),
          pwd:'',
          newPwd:''
        },
        confirmPwd:''
      }
    },
    methods: {
      onSubmit(){
        if (this.confirmPwd === this.form.newPwd) {
          this.$http.post('/user/passwordEdit', this.form)
            .then((res) => {
              if (res.data.status === 200) {
                this.$message.success('提交成功')
                this.close()
              } else {
                this.$message.error(res.data.msg)
              }
            })
        } else {
          this.$message.error('密码不一致请重新输入')
          this.form.newPwd = ''
          this.confirmPwd = ''
        }
      },
      close(){
        console.log('关闭')
        this.$emit('handleClose', false)
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
      width: 400px;
      height: 350px;
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
        .modal-upload{
          height: 47%;
          padding: 30px;
          display: flex;
          flex-direction: column;
          .modal-file{
          
          }
          .modal-photo{
            background-color: red;
            width: 100%;
            height: 100%;
            max-width: 320px;
            max-height: 206px;
          }
          .avatar-uploader .el-upload {
            border: 1px dashed #d9d9d9;
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
          }
          .avatar-uploader .el-upload:hover {
            border-color: #409EFF;
          }
          .avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            line-height: 178px;
            text-align: center;
          }
          .avatar {
            width: 178px;
            height: 178px;
            display: block;
          }
        }
        .modal-btn{
          text-align: center;
        }
      }
    }
  }
</style>
