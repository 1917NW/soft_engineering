<template>
<div class="student">
 <div class="contain">
        <div class="item" v-for="nav,index in navs" :key="index" @click="changeRouter(index)">{{ nav }}</div>
        
 </div>
 <el-button
            type="primary"
            circle
            class="user"
            icon="el-icon-user"
            @click="infoShow"
            ></el-button
          >
          <el-dialog title="修改个人信息" @close="clearForm" :visible.sync="dialogFormVisible">
      <el-form :model="userForm"  ref="userFormRef">

        <el-form-item label="身份证号" :label-width="formLabelWidth" prop="userIdNumber">
          <el-input v-model="userForm.userIdNumber" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
          <el-input v-model="userForm.userName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item v-if="userForm.id == null||userForm.id == undefined" label="密码" :label-width="formLabelWidth" prop="password">
          <el-input v-model="userForm.userPassword" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="userForm.userPhone" autocomplete="off"></el-input>
        </el-form-item>
     

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUser"
          >确 定</el-button
        >
      </div>
    </el-dialog>
          
 <router-view style="margin-top:20px"></router-view>
</div>
</template>

<script>
import infoApi from "@/api/info"
import userApi from "@/api/admin/user_management.js"
export default {
    data() {
      return {
         navs : ["考试中心", "我的考试","考试成绩"],
         mapNavs : ["/studentExamList","/studentMyExams","/studentExamScore"],
         dialogFormVisible : false,
      userForm : {
        userIdNumber:"",
        userName : "",
        userPassword:"",
        userPhone:"",
        userRole : 1
      },
      };
    },
    methods: {
    changeRouter(index){
        console.log(this.mapNavs[index]);
        this.$router.push(this.mapNavs[index],()=>{},()=>{});
    },
    infoShow(){
      infoApi.getInfo().then(res => {
        this.userForm = res.data
      })
      this.dialogFormVisible = true;
    },
    clearForm(){
      this.userForm = {};
      this.$refs['userFormRef'].clearValidate();
    },
    saveUser(){
      // 检测是否所有输入内容都通过检测，如果都通过检测，再去提交
      this.$refs['userFormRef'].validate((valid) => {
          if (valid) {
            userApi.saveUser(this.userForm).then(res => {
              this.$message({
                message : res.message,
                type : 'success'
              })
            });
            
            this.dialogFormVisible = false;
            
            
          } else {
            console.log('error submit!!');
            return false;
          }
        });
       
    },
    },
    created(){
        this.$router.push("/studentExamList",()=>{},()=>{});
    }
}
</script>

<style scoped>
    .student{
        height: 100%;
    }
    .contain{
        display: flex;
        width: 60%;
        height: 5%;
        margin: 0 auto;
        border:1px solid black;
    }
    .item{
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center
    }
    .item:hover{
        background-color: skyblue;
    }
    .user{
    float: right;
    margin-top: -30px;
    margin-right: 30px;

  }
</style>