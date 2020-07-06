<template>
  <div class='flywork-layout'>
    <app-header></app-header>
    <div class='left-nav'>
      <router-link tag="span" to='/userLayout/repair' class='nav-tab'>
        报修
      </router-link>
      <router-link tag="span" to='/userLayout/pay' class='nav-tab'>
        自助缴费
      </router-link>
      <router-link tag="span" to='/userLayout/opinion' class='nav-tab'>
        业主建议
      </router-link>
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
      }
    },
    watch:{
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
      border-right: 1px solid rgba(235,236,240,1);
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
        margin-left: 10px;
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
      width: calc(100vw - 169px);
      height: calc(100vh - 71px);
    }
  }
</style>
