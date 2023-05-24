<template>
    <div class="app">
      <!-- 头部 -->
      <myHeader></myHeader>
  
      <el-container 
        style="height: 100vh; 
        border: 1px solid #eee; 
        margin: 0;
        border: 0;
        padding: 0;">     <!-- style： 隐藏滚动条  去掉白边-->
        <!-- 侧边栏 -->
        <el-aside style="width: 20%;">
          <asideNav></asideNav>
        </el-aside>
        <!-- 主页面 -->
        <el-main>
          <router-view></router-view>  
        </el-main>
      </el-container>
    </div>
  </template>
   
  <script>
  import myHeader from "@/components/admin/Header.vue"
  import asideNav from "@/components/admin/AsideNav.vue"
  
  export default {
    name: "App",
    components: {
      myHeader,
      asideNav,
    }
  }
  </script scoped>
  
  <style>
    html,
    body{
      height: 100%;
      margin: 0;
      border: 0;
      padding: 0;
    }
  </style>
  