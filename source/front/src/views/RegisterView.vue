<template>
    <div class="login-container">
   <div class="box">
        <h2>Register</h2>
        <el-form ref="userForm" :model="userForm" label-width="80px" style="margin-top:10px">
            <el-form-item label="身份证号">
            <el-input placeholder="请输入身份证号" v-model="userForm.userIdNumber"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
            <el-input placeholder="请输入用户名" v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input placeholder="请输入密码" v-model="userForm.userPassword" show-password></el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input placeholder="请再次输入密码" v-model="userPasswordR" show-password></el-input>
        </el-form-item>
        <el-form-item label="手机">
            <el-input placeholder="请输入手机" v-model="userForm.userPhone"></el-input>
        </el-form-item>
        </el-form>
     
        <el-button type="primary" @click="onSubmit">注册</el-button>
        <div class="toLogin"><router-link to="/login" class="line">返回登录</router-link></div>
    </div>
</div>
</template>

<script>
import api from "../api/student/register.js"
export default {
    data(){
        return {
            userForm :{
                userIdNumber:'11111',
                userName:'admin',
                userPassword:'123456',
                userRole:"3",
                userPhone:"10086"
            },
            userPasswordR:''
        }
    },
    methods : {
        
        onSubmit() {
        if(this.userForm.userPassword == this.userPasswordR){
        api.register(this.userForm).then(res => {
                this.$router.push("/student");      
        });
    }else
        console.log(456)
      }
    }

}
</script>

<style scoped>
.box{
    width: 30%;
    margin:0 auto;
    align-items: center;
    
    background-color: #fff;
    padding-top:40px;
    padding-right: 30px;
    padding-left: 20px;
    padding-bottom: 20px;
    border-radius: 10px;
    opacity: 0.7;
}

.login-container {
  height: 100%;
  width: 100%;
  overflow: hidden;
  background-image: url("../assets/fox.jpg");
  background-size: cover;
  display: flex;
  align-items:center;
}
.toLogin{
    margin-top: 10px;
}
</style>