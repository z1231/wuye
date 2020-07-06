<template>
  <div class='setting-modal'>
    <div class='modal-content'>
      <div class='modal-header'>新增建议</div>
      <div class="modal-form">
        <div class="modal-upload">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="姓名">
              <el-input v-model="form.name" disabled></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="form.mobile" disabled></el-input>
            </el-form-item>
            <el-form-item label="位置">
              <el-input v-model="form.address" disabled></el-input>
            </el-form-item>
            <el-form-item label="内容">
              <el-input
                type="textarea"
                placeholder="请输入内容"
                v-model="form.content"
                maxlength="200"
                show-word-limit>
              </el-input>
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
          code: sessionStorage.getItem('code'),
          name: sessionStorage.getItem('name'),
          mobile: sessionStorage.getItem('mobile'),
          address: sessionStorage.getItem('address'),
          content:''
        }
      }
    },
    methods: {
      onSubmit(){
        this.$http.post('/user/adviceAdd', this.form)
          .then((res) => {
            if (res.data.status === 200) {
              this.$message.success('提交成功')
              this.close()
            }
          })
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
        .modal-upload{
          height: 75%;
          padding: 30px;
          display: flex;
          flex-direction: column;
          .el-icon-plus{
            border: 1px solid #d0dbe5;
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
          padding-bottom: 30px;
          text-align: center;
        }
      }
    }
  }
</style>
