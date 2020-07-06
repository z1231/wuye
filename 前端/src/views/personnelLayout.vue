<template>
  <div class='flywork-layout'>
    <app-header></app-header>
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
    .right-view {
      position: absolute;
      top: 71px;
      width: 100%;
      height: calc(100vh - 71px);
    }
  }
</style>
