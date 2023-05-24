<template>
    <div>
  <el-card id="searchBox">
      <el-row>
        <el-col :span="20" align="left"
          ><el-input
            v-model="searchModel.username"
            placeholder="请输入用户名"
            clearable
          ></el-input>
          <el-input
            v-model="searchModel.phone"
            placeholder="请输入电话"
            clearable
          ></el-input>
          <el-input
            v-model="searchModel.userIdNumber"
            placeholder="请输入身份证号"
            clearable
          ></el-input>
          <el-button
            type="primary"
            round
            icon="el-icon-search"
            @click="getUserList"
            >搜索</el-button
          >
        </el-col>
        <el-col :span="4" align="right">
          <el-button type="primary" icon="el-icon-plus" circle @click="openEditUI(null)"></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card id="List">
      <el-table :data="userList" style="width: 100%">
        <el-table-column label="ID" width="180">
          <template slot-scope="scope">
            {{
              (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1
            }}
          </template>
        </el-table-column>
        <el-table-column prop="userName" label="用户名" width="180">
        </el-table-column>
        <el-table-column prop="userPhone" label="电话" width="180">
        </el-table-column>
        <el-table-column prop="userIdNumber" label="身份证号" width="180">
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope = "scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="openEditUI(scope.row.userId)"></el-button>
           <el-button type="danger" icon="el-icon-delete" circle @click="deleteUser(scope.row)"></el-button>
          </template>
          
           </el-table-column>
      </el-table>
    </el-card>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>

    <el-dialog :title="title" @close="clearForm" :visible.sync="dialogFormVisible">
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
import userApi from "@/api/admin/user_management.js"
export default {
    data() {
    return {
      searchModel: {
        
        phone: "",
        username: "",
        userIdNumber:"",
        userRole:3,
        pageNo: 1,
        pageSize: 10,
      },
      formLabelWidth : "120px",
      title:"",
      dialogFormVisible : false,
      userForm : {
        userIdNumber:"",
        userName : "",
        userPassword:"",
        userPhone:"",
        userRole : 3
      },
      userList: [],
      total: 0,
      rules : {
        username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, message: '长度不少于6个字符', trigger: 'blur' }
           
          ],
          phone: [
            { required: true, message: '请输入电话号码', trigger: 'blur' },
           
          ],
          userIdNumber: [
            { required: true, message: '请输入身份证号', trigger: 'blur' },
            
          ],
      }
    };
  },
  methods: {
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getUserList();
    },

    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getUserList();
    },

    getUserList() {
      userApi.getUserList(this.searchModel).then((response) => {
        this.userList = response.data.rows;
        this.total = response.data.total;
      });
    },
    openEditUI(id){
      if(id == null){
        this.title = "新增用户";
      }else{
        this.title= "修改用户";
        userApi.getUserById(id).then(res => {
          this.userForm = res.data;
        });
      }
      
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
              this.getUserList()
            });
            
            this.dialogFormVisible = false;
            
            
          } else {
            console.log('error submit!!');
            return false;
          }
        });
       
    },
    deleteUser(user){
      this.$confirm(`你确认删除用户${user.userName}`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          userApi.deleteUserById(user.userId).then(res => {
            this.$message({
            type: 'success',
            message: res.message
          });
          this.getUserList();
          })
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    }
  },
  created() {
    this.getUserList();
  },
}
</script>

<style scoped>
#searchBox .el-input {
  width: 200px;
  margin-right: 10px;
}
#List{
    margin-top: 10px;
}
.el-pagination{
    margin-top: 10px;
    text-align: left;
}
</style>