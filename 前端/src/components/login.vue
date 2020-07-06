<template>
  <div class="login">
    <div class='modal-content'>
      <div class="login-left">
        <div><img class="login-logo" src="../../public/img/logo.png"></div>
        <div class="login-text">煌天物业</div>
      </div>
      <div class="login-right">
        <div class="login-handle">
          <div >登录</div>
          <el-input v-model="login.code" placeholder="请输入账号" class='lg-input'></el-input>
          <el-input v-model="login.password" placeholder="请输入密码" class='lg-input' show-password></el-input>
          <div class="lg-radio">
            <el-radio v-model="login.role" label="1">物业</el-radio>
            <el-radio v-model="login.role" label="2">业主</el-radio>
            <el-radio v-model="login.role" label="3">员工</el-radio>
          </div>
          <el-button type="primary" @click="confirm"  class='lg-input'>确定</el-button>
          <div class='modal-foot'>
            <div>还没账号？</div>
            <div class="registered" @click="showModal">立即注册</div>
          </div>
        </div>
      </div>
    </div>
    <Register v-if="showRegister" @handleClose="handleClose"></Register>
  </div>
</template>

<script>
  import Register from './register'
  export default {
    name: 'HelloWorld',
    data() {
      return{
        login:{
          code:'',
          password:'',
          role:'',
          valiCode:''
        },
        showRegister:false
      }
    },
    components:{
      Register
    },
    methods:{
      showModal () {
        this.showRegister = true
      },
      confirm() {
        if (this.login.role !== '') {
          this.$http.post('/user/login',this.login)
          .then((res) => {
            console.log('■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■')
            console.log('res:', res)
            if (res.data.status === 200) {
              this.$message.success('恭喜你，登录成功')
              sessionStorage.setItem('code', `${this.login.code}`);
              sessionStorage.setItem('status', `${this.login.role}`);
              sessionStorage.setItem('name', res.data.data.name);
              sessionStorage.setItem('mobile', res.data.data.mobile);
              sessionStorage.setItem('address', res.data.data.address);
              if (sessionStorage.getItem('status') === '1') {
                this.$router.push(`/layout`)
              } else if (sessionStorage.getItem('status') === '2') {
                this.$router.push(`/userLayout`)
              } else if (sessionStorage.getItem('status') === '3') {
                this.$router.push(`/personnelLayout`)
              }
            } else {
              this.$message.error(res.data.msg)
              this.login.code = ''
              this.login.password = ''
            }
            console.log('■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■')
          })
        } else {
          this.$message.error('请选择角色！')
        }
      },
      handleClose(data){
        this.showRegister = data
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .login {
    width: calc(100vw);
    height: calc(100vh);
    min-width: 1150px;
    min-height: 650px;
    background: url("../../public/img/login.png");
    background-size: 100% 100%;
    .modal-content {
      width:38%;
      background:rgba(255,255,255,1);
      box-shadow:8px 8px 29px 0px rgb(13, 86, 199);
      border-radius:3px;
      z-index: 101;
      color:rgba(59,86,129,1);
      left:15%;
      top: 25%;
      position: relative;
      display: flex;
      .login-left{
        width: 40%;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
        flex-direction: column;
        border-right: 1px solid rgba(235,236,240,0.95);
        .login-logo{
          width: 100px;
        }
        .login-text{
          margin-top: 10px;
          font-size: 30px;
        }
      }
      .login-right{
        width: 60%;
        .modal-header {
          margin: 0;
          padding-left: 20px;
          display: flex;
          align-items: center;
          font-size:19px;
          font-family:PingFang-SC;
          font-weight:400;
          color:rgba(59,86,129,1);
          border-bottom: 1px solid rgba(235,236,240,0.95);
        }
        .login-handle{
          font-size: 30px;
          margin-top: 20%;
          text-align: center;
          .lg-radio{
            margin-left: 10%;
            text-align: left;
          }
        }
        .lg-input {
          width: 80%;
          height: 5.4%;
          margin-top: 2%;
        }
        .modal-foot {
          align-items: flex-end;
          padding: 20px;
          justify-content: flex-end; /*垂直排列*/
          top: 20px;
          right: 35px;
          bottom: 14px;
          display: flex;
          font-size: 10px;
          .confirm-button {
            margin-right: 10px;
          }
          .registered{
            color: #6e73ff;
            cursor: pointer;
          }
          .registered:hover{
            color: #1b2fff;
          }
        }
      }
    }
  }
</style>
