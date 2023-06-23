<template>
  <div>
    <el-card id="searchBox">
        <el-row>
          <el-col :span="20" align="left"
            ><el-input
              placeholder="用户id"
              clearable
              v-model="searchModel.userIdNumber"
            ></el-input>
          
            <el-button
              type="primary"
              round
              icon="el-icon-search"
              @click="getScoreList"
              >搜索</el-button
            >
           
          </el-col>
          
        </el-row>
      </el-card>

      
      <el-card id="List">
        <el-table :data="studentScoreList" style="width: 100%">
        
  
          <el-table-column prop="userIdNumber" label="学生身份证号" width="180">
            
          </el-table-column>

          <el-table-column prop="userName" label="学生姓名" width="180">
            
          </el-table-column>

          <el-table-column  label="考试状态" width="180">
            <template slot-scope="scope">
          <el-tag v-if="scope.row.status==0" type="danger">未批阅</el-tag>
          <el-tag v-if="scope.row.status==1" type="success">已批阅</el-tag>
        </template>
          </el-table-column>

          <el-table-column prop="score" label="学生成绩" width="180">
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
  
  </div>
</template>

<script>
import scoreApi from "@/api/admin/score_management"
export default {
    data(){
      return {
        total:0,
        searchModel:{
                  userIdNumber:"",
                  examId:"",
                  pageNo: 1,
                  pageSize: 10,
              },
              studentScoreList:[]
      }
    },
    methods:{
      search(){
        scoreApi.getScoreList(this.searchModel).then(res => {
          this.studentScoreList = res.data.rows
          this.total = res.data.total
          console.log(res)
        })
      },
      handleSizeChange(pageSize) {
        this.searchModel.pageSize = pageSize;
        this.getScoreList();
      },
  
      handleCurrentChange(pageNo) {
        this.searchModel.pageNo = pageNo;
        this.getScoreList();
      },
      getScoreList(){
        this.search()
      }
    },
    created(){
      this.searchModel.examId = this.$route.query.examid
      this.search();
    }
}
</script>

<style>

</style>