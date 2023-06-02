<template>
  <div>
  <h1>Admin <el-button
            type="primary"
            circle
            class="user"
            icon="el-icon-user"
            @click="infoShow"
            ></el-button
          ></h1>

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
  </div>
</template>

<script>
import infoApi from "@/api/info"
import userApi from "@/api/admin/user_management.js"
export default {
  data(){
    return {
      formLabelWidth : "120px",
      dialogFormVisible : false,
      userForm : {
        userIdNumber:"",
        userName : "",
        userPassword:"",
        userPhone:"",
        userRole : 1
      },
    }
  },
  methods:{
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
  }
}
</script>

<style scoped>
  h1{
    background-color: orange;
    height: 100px;
    line-height: 100px;
  }
  .user{
    float: right;
    margin-top: 30px;
    margin-right: 20px;

  }
</style>