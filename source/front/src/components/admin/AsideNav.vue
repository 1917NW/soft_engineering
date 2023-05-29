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
            <el-submenu index="">
              <template slot="title">
                <i class="el-icon-info"></i>
                <span>用户管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/userlist" >学生管理</el-menu-item>
                <el-menu-item index="/teacherlist">教师管理</el-menu-item>
                <el-menu-item index="/adminlist">管理员管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

            <el-menu-item index="/questionlist">
              <i class="el-icon-question"></i>
              <span slot="title">试题管理</span>
    
            </el-menu-item>

            <el-menu-item index="/paperlist">
              <i class="el-icon-document"></i>
              <span slot="title">试卷管理</span>
            </el-menu-item>

            <el-menu-item index="/examlist">
              <i class="el-icon-menu"></i>
              <span slot="title">考试管理</span>
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