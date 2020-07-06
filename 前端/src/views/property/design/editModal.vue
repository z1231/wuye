<template>
  <div class='setting-modal'>
    <div class='modal-content'>
      <div class='modal-header'>修改</div>
      <div class="modal-form">
        <!--员工-->
        <div class="modal-upload" v-if="classify === 0">
          <el-form ref="form" :model="userForm" label-width="80px">
            <el-form-item label="账号">
              <el-input v-model="userForm.code" disabled></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="userForm.password"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="userForm.name"></el-input>
            </el-form-item>
            <el-form-item label="身份证号">
              <el-input v-model="userForm.idCard"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="userForm.mobile"></el-input>
            </el-form-item>
            <el-form-item label="部门">
              <el-select v-model="userForm.org" style="width: 100%" placeholder="请选择部门">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="岗位">
              <el-input v-model="userForm.work"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <!--业主-->
        <div class="modal-upload" v-else>
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="账号">
              <el-input v-model="form.code" disabled></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="身份证号">
              <el-input v-model="form.idCard"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="form.mobile"></el-input>
            </el-form-item>
            <el-form-item label="位置">
              <el-input v-model="form.address"></el-input>
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
        form:{
          id: null,
          code:'',
          password:'',
          name:'',
          idCard:'',
          mobile:'',
          address:''
        },
        userForm: {
          id: null,
          code:'',
          password:'',
          name:'',
          idCard:'',
          mobile:'',
          org: '',
          work: ''
        },
        options: [{
          value: '财务部',
          label: '财务部'
        }, {
          value: '商务部',
          label: '商务部'
        }, {
          value: '后勤部',
          label: '后勤部'
        }, {
          value: '人力资源部',
          label: '人力资源部'
        }],
      }
    },
    props: [ 'classify', 'row' ],
    created() {
      console.log('this.row', this.row)
      // 等于1 是业主， 0是员工
      if (this.classify) {
        this.form = this.row
      } else {
        this.userForm = this.row
      }
    },
    methods: {
      onSubmit(data){
        if (data) {
          // 等于1 是业主， 0是员工
          this.$http.post('/user/ownerUserUpdate',this.form)
            .then((res) => {
              if (res.data.status === 200) {
                this.$message.success('恭喜你，修改成功')
                this.close()
              } else {
                this.$message.error(res.data.msg)
              }
            })
        } else {
          // 员工
          this.$http.post('/user/adminUserUpdate',this.userForm)
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
        this.$emit('handleModal')
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
      width: 500px;
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
