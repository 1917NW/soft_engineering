<template>
    <div class="aside" style="height: 100%; width: 100%;">
      <el-row class="tac" style="height: 100%; width: 100%;">
        <el-col :span="12" style="height: 100%; width: 100%;">
          <el-menu 
            default-active="this.$route.path" router
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#87ceeb"
            text-color="#fff"
            active-text-color="#ffd04b"
            style="height: 100%; width: 100%;">

            <el-menu-item index="/tquestionlist">
              <i class="el-icon-menu"></i>
              <span slot="title">试题管理</span>
    
            </el-menu-item>

            <el-menu-item index="/tpaperlist">
              <i class="el-icon-menu"></i>
              <span slot="title">试卷管理</span>
            </el-menu-item>

            <el-menu-item index="/readoverexam">
              <i class="el-icon-menu"></i>
              <span slot="title">考试批改</span>
            </el-menu-item>


          </el-menu>
        </el-col>
      </el-row>
    </div>
  </template>
  
  <script>
  
  export default {
      methods: {
        handleOpen(key, keyPath) {
          console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
          console.log(key, keyPath);
        },
        
      }
    }
  </script>
  
  <style>
 
</style>