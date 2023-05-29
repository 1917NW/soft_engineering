<template>
  <div class="myExam">
    <h1>我的考试</h1>
    <div class="myExamList">
      <el-card class="box-card">
      <el-table
      :data="MyExamList"
      style="width: 100%">
      <el-table-column
        prop="examName"
        label="考试名称"
        width="180">
      </el-table-column>

      <el-table-column
        prop="examStartTime"
        label="考试开始时间"
        width="180">
      </el-table-column>

      <el-table-column
        prop="examLastTime"
        label="考试结束时间"
        width="180">
      </el-table-column>
      <el-table-column
        label="操作"
        width="180">
      <template slot-scope="scope">
        <router-link target="_blank" :to="{path:'/do',query:{id:scope.row.examPaperId, token:Token,examId:scope.row.examId}}">
          <el-tag  type="" effect="dark">进入考试</el-tag>
        </router-link>
        
      </template>
      </el-table-column>
    </el-table>
  </el-card>
    </div>
  </div>
  
</template>

<script>

import examApi from "@/api/student/examlist"
export default {
  data(){
    return {
      MyExamList:[],
      Token:""
    }
  },
  methods:{
    getMyExamList(){
      examApi.getMyExamList().then(r => {
        this.MyExamList = r.data;
      })
    }
  },
  created(){
    this.getMyExamList();
    this.Token = this.$store.state.token
  }
}
</script>

<style>
.myExam{
  width: 100%;
}
.myExamList{
  width: 60%;
  margin: 0 auto;
  margin-top: 40px;
}

</style>