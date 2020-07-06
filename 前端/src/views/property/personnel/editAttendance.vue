<template>
  <div class='setting-modal'>
    <div class='modal-content'>
      <div class='modal-header'>修改</div>
      <div class="modal-form">
        <!--员工-->
        <div class="modal-upload">
          <el-form ref="form" :model="userForm" label-width="120px">
            <el-form-item label="姓名">
              <el-input v-model="userForm.name" disabled></el-input>
            </el-form-item>
            <el-form-item label="部门">
              <el-input v-model="userForm.org" disabled></el-input>
            </el-form-item>
            <el-form-item label="岗位">
              <el-input v-model="userForm.work" disabled></el-input>
            </el-form-item>
            <el-form-item label="累计迟到次数">
              <el-input v-model="userForm.lateNum"></el-input>
            </el-form-item>
            <el-form-item label="累计早退次数">
              <el-input v-model="userForm.earlyNum"></el-input>
            </el-form-item>
            <el-form-item label="累计旷工次数">
              <el-input v-model="userForm.absentNum"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div class="modal-btn">
          <el-button type="primary" @click="onSubmit(classify)">确定</el-button>
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
        userForm: {
          id: null,
          name:'',
          idCard:'',
          mobile:'',
          org: '',
          work: '',
          lateNum: '',
          earlyNum: '',
          absentNum: ''
        }
      }
    },
    props: [ 'row' ],
    created() {
      this.userForm = this.row
    },
    methods: {
      onSubmit () {
        this.$http.post('/user/checkUpdate',this.userForm)
          .then((res) => {
            if (res.data.status === 200) {
              this.$message.success('恭喜你，修改成功')
              this.close()
            } else {
              this.$message.error(res.data.msg)
            }
          })
      },
      close () {
        console.log('关闭')
        this.$emit('handleClose')
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
      width: 700px;
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
          padding:20px 0;
        }
      }
    }
  }
</style>
