<template>
  <div class="top-header" style="-webkit-app-region: drag">
    <div class='left'>
      <img src="../../public/img/logo.png" class='production-logo'/>
      <p class='production-version'>{{version}}</p>
      <div class="top-text" v-if="status === '1'">物业端1.0.1</div>
      <div class="top-text" v-if="status === '2'">业主端1.0.1</div>
      <div class="top-text" v-if="status === '3'">员工端1.0.1</div>
    </div>
    <div class='right'>
      {{code}}
      <el-dropdown class='menu' trigger="click"  @command="handleCommand">
        <div  class="el-dropdown-link">
          <img src="../../public/img/menu.png"/>
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="password">修改密码</el-dropdown-item>
          <el-dropdown-item command="infoManage">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <password v-if="showPassword" @handleClose="closePassword"></password>
  </div>
</template>

<script>
  import password from './change/password'

  export default {
    name: 'app-header',
    data () {
      return {
        version: '煌天物业',
        isMaximized1: false,
        isMaximized2: false,
        name: true,
        showPassword:false,
        user:'',
        status: '',
        code: ''
      }
    },
    computed: {
    },
    components: { password },
    mounted () {
      this.status = sessionStorage.getItem('status')
      this.code = sessionStorage.getItem('code')
    },
    updated () {
    },
    methods: {
      handleCommand (command) {
        if( command === 'password') {
          this.showPassword = true
        } else {
          this.$confirm('确定要退出吗', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            sessionStorage.clear()
            this.$router.push('/')
          }).catch(() => {

          })
        }
      },
      closePassword(data){
        this.showPassword = data
      }
    }
  }
</script>

<style lang="scss" scoped>
  .top-header {
    height: 71px;
    line-height: 25px;
    width: 100%;
    display: flex;
    align-items: center;
    background: #fff;
    border-bottom: 1px solid rgba(235,236,240,1);
    color:rgba(59,86,129,1);
    position: relative;
    z-index: 101;
  }
  .left {
    display: flex;
    align-items: center;
    position: absolute;
    left: 31px;
    .production-logo{
      width: 40px;
    }
    .production-version {
      display: inline-block;
      margin: 0;
      margin-left: 10px;
      font-family:PingFang-SC;
      font-weight:600;
      font-size:32px;
    }
    .top-text{
      margin-left: 10px;
      margin-top: 10px;
    }
  }
  .right {
    position: absolute;
    height: 100%;
    right: 5px;
    display: flex;
    line-height: 71px;
  }
  .current-time,
  .zoomout,
  .close,
  .fullscreen,
  .menu {
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
    -webkit-app-region: no-drag;
    font-weight: lighter;
    margin:0 15px;
  }
  .current-time {
    margin-right: 35px;
    font-size:16px;
    font-family:PingFang-SC;
    font-weight:bold;
    color:rgba(80,102,139,1);
    line-height:44px;
  }
  .zoomin {
    width: 19px;
    height: 15px;
  }
</style>
