<template>
    <div>
        <h1>批阅试卷</h1>
        <el-card id="searchBox" style="margin-top: 20px;width: 80%;">
        <el-row>
          <el-col :span="20" align="left"
            ><el-input
              placeholder="用户id"
              clearable
              
            ></el-input>
           
            <el-button
              type="primary"
              round
              icon="el-icon-search"
             
              >搜索</el-button
            >
            
          </el-col>
          
        </el-row>
      </el-card>

      <el-card id="List">
        <el-table :data="answerList" style="width: 100%">
        
  
          <el-table-column prop="examId" label="考试ID" width="180">
            
          </el-table-column>
  
          <el-table-column prop="userId" label="用户ID" width="180">
          </el-table-column>
  
    
  
          <el-table-column label="操作" width="180">
            <template slot-scope = "scope">
                <router-link target="_blank" :to="{path:'/point',query:{id:JSON.stringify(scope.row), token:Token}}">
                 <el-tag  type="" effect="dark">查看</el-tag>
                </router-link>
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

    </div>
    
  
</template>

<script>
import answerApi from '@/api/teacher/answer';
export default {
    data(){
        return {
            Token:"",
            answerList : [],
            searchModel:{
                  userId: "",
                  pageNo: 1,
                  pageSize: 10,
              },
            total:0
        }
    },
    methods:{
        handleSizeChange(pageSize) {
        this.searchModel.pageSize = pageSize;
        this.search();
      },
  
      handleCurrentChange(pageNo) {
        this.searchModel.pageNo = pageNo;
        this.search();
      },
      search(){

      },
     

    
     
    },
    created(){
        let examId = this.$route.query.examId;
        this.Token = this.$store.state.token
        console.log(examId)
        answerApi.getUserAnswerList({
            examId : examId,
            pageNo: this.searchModel.pageNo,
            pageSize : this.searchModel.pageSize
        }).then(res => {
            
            this.answerList = res.data.rows
        })
    }
}
</script>

<style scoped>
.el-pagination{
    margin-top: 20px;
    text-align: left;
}

</style>