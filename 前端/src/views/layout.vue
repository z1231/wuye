<template>
  <div class='flywork-layout'>
    <app-header></app-header>
    <div class='left-nav'>
      <router-link tag="span" to='/layout/home' class='nav-tab-title bold nav-tab'>
        首页
      </router-link>
      <span  class='nav-tab-title bold' @click="personnel = !personnel">
        人事管理
        <i :class="[personnel?'el-icon-arrow-down down':'el-icon-arrow-up up']"></i>
      </span>
      <transition name="draw">
        <el-collapse-transition>
          <div class="box" v-show="personnel">
            <router-link tag="span" to='/layout/position' class='nav-tab'>
              岗位管理
            </router-link>
            <router-link tag="span" to='/layout/attendance' class='nav-tab'>
              考勤管理
            </router-link>
            <router-link tag="span" to='/layout/staff' class='nav-tab'>
              员工管理
            </router-link>
          </div>
        </el-collapse-transition>
      </transition>
      <span  class='nav-tab-title bold'  @click="service = !service">
        业务管理
        <i :class="[service?'el-icon-arrow-down down':'el-icon-arrow-up up']"></i>
      </span>
      <transition name="draw">
        <el-collapse-transition>
          <div class="box" v-show="service">
            <router-link tag="span" to='/layout/repair' class='nav-tab'>
              维修管理
            </router-link>
            <router-link tag="span" to='/layout/car' class='nav-tab'>
              车位管理
            </router-link>
            <router-link tag="span" to='/layout/pay' class='nav-tab'>
              自助缴费管理
            </router-link>
            <router-link tag="span" to='/layout/storehouse' class='nav-tab'>
              库存管理
            </router-link>
          </div>
        </el-collapse-transition>
      </transition>
      <span  class='nav-tab-title bold'  @click="security = !security">
        安保保洁管理
        <i :class="[security?'el-icon-arrow-down down':'el-icon-arrow-up up']"></i>
      </span>
      <transition name="draw">
        <el-collapse-transition>
          <div class="box" v-show="security">
            <router-link tag="span" to='/layout/scheduling' class='nav-tab'>
              安保保洁排班
            </router-link>
          </div>
        </el-collapse-transition>

      </transition>
      <span  class='nav-tab-title bold'  @click="community = !community">
        社区服务
        <i :class="[community?'el-icon-arrow-down down':'el-icon-arrow-up up']"></i>
      </span>
      <transition name="draw">
        <el-collapse-transition>
          <div class="box" v-show="community">
            <router-link tag="span" to='/layout/tips' class='nav-tab'>
              提醒服务
            </router-link>
            <router-link tag="span" to='/layout/proposal' class='nav-tab'>
              业主建议
            </router-link>
          </div>
        </el-collapse-transition>
      </transition>
      <span  class='nav-tab-title bold'  @click="design = !design">
        系统设计
        <i :class="[design?'el-icon-arrow-down down':'el-icon-arrow-up up']"></i>
      </span>
      <transition name="draw">
        <el-collapse-transition>
          <div class="box" v-show="design">
            <router-link tag="span" to='/layout/user' class='nav-tab'>
              用户管理
            </router-link>
            <router-link tag="span" to='/layout/information' class='nav-tab'>
              业主信息管理
            </router-link>
          </div>
        </el-collapse-transition>
      </transition>
    </div>
    <router-view class='right-view'></router-view>
  </div>
</template>
<script>
  import AppHeader from '@/components/header.vue'
  export default {
    name: 'Layout',
    data () {
      return {
        personnel: false,
        service: false,
        security: false,
        community: false,
        design: false
      }
    },
    watch:{
      personnel(){
        if(this.personnel){
          this.service=false
          this.security=false
          this.community=false
          this.design=false
        }
      },
      service(){
        if(this.service){
          this.personnel=false
          this.security=false
          this.community=false
          this.design=false
        }
      },
      security(){
        if(this.security){
          this.service=false
          this.personnel=false
          this.community=false
          this.design=false
        }
      },
      community(){
        if(this.community){
          this.service=false
          this.security=false
          this.personnel=false
          this.design=false
        }
      },
      design(){
        if(this.design){
          this.service=false
          this.security=false
          this.community=false
          this.personnel=false
        }
      }
    },
    computed: {
    },
    components: {
      AppHeader
    },
    created () {
    },
    mounted () {
    },
    methods: {
    }
  }
</script>
<style lang="scss" scoped>
  .flywork-layout {
    background: #f5f5f5;
    .left-nav {
      width: 169px;
      background: #fff;
      z-index: 99;
      position: absolute;
      top: 71px;
      bottom: 0px;
      display: flex;
      flex-direction: column;
      align-items: center;
      .down{
        transition: all 0.5s;
        position: absolute;
        right: 10px;
      }
      .up{
        transition: all 0.5s;
        transform:rotate(-180deg);
        position: absolute;
        right: 10px;
      }
      .nav-tab-title{
        margin-top: 10px;
        display: flex;
        justify-content: start;
        align-items: center;
        padding-left: 20px;
        box-sizing: border-box;
        width:154px;
        height:39px;
        border-radius:3px;
        cursor: pointer;
        font-size: 14px;
        color: #50668B;
      }
      .nav-tab {
        margin-top: 0px;
        margin-left: 20px;
        display: flex;
        justify-content: start;
        align-items: center;
        padding-left: 20px;
        box-sizing: border-box;
        width:154px;
        height:39px;
        border-radius:3px;
        cursor: pointer;
        font-size: 14px;
        color: #50668B;
      }
      .nav-tab:hover{
        background: rgb(219, 236, 255);
        color: rgba(77,161,255,1);
      }
      .bold {
        font-weight: bold;
        cursor: pointer;
      }
      .box{
        overflow: hidden;
      }
      .draw-enter-active, .draw-leave-active {
        transition: all 0.5s ease;
      }
      .draw-enter, .draw-leave-to {
        height: 0;
      }
      .router-link-active {
        background:rgba(77,161,255,.2);
        color: #4DA1FF
      }
    }
    .right-view {
      position: absolute;
      left: 169px;
      top: 71px;
      width: calc(100vw - 209px);
      height: calc(100vh - 111px);
      background: rgb(248,250,251);
      padding: 20px;
    }
  }
</style>
