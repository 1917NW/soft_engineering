
<template>

<div class="login-container">
    
    <div class="box">
        <h2>login</h2>
        <el-form ref="userForm" :model="userForm" label-width="80px">
        <el-form-item label="身份证号">
            <el-input placeholder="请输入身份证号" v-model="userForm.userIdNumber"></el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input placeholder="请输入密码" v-model="userForm.userPassword" show-password></el-input>
        </el-form-item>
      
        </el-form>
     
        <el-button type="primary" @click="onSubmit">登录</el-button>
        <div class="toRegister"> <router-link to="/register">还没有账号，注册一个？</router-link></div>
       
    </div>

</div>

  
</template>

<script>
import api from "../api/login.js"
export default {
    data(){
        return {
            userForm :{
                userIdNumber:'123456',
                userPassword:'123456'
            }
        }
    },
    methods : {
        
        onSubmit() {
        api.login(this.userForm).then(res => {
                console.log(res);
                if(res.data.role == "1"){
                    console.log(1);
                    this.$router.push("/admin");
                }
                if(res.data.role == "3"){
                    console.log(1);
                    this.$router.push("/student");
                }
                
                
                
        })
       
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
.toRegister{
    margin-top: 10px;
}
</style>