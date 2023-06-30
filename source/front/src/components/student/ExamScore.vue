<template>
  <div class="myExam">
    <h1>考试成绩</h1>
    <div class="myExamList">
      <el-card class="box-card">
      <el-table
      :data="ExamScoreList"
      style="width: 100%">

      <el-table-column
        prop="exam.examId"
        label="考试Id"
        width="180">
      </el-table-column>

      <el-table-column
        prop="exam.examName"
        label="考试名称"
        width="180">
      </el-table-column>

      <el-table-column
        prop="exam.examStartTime"
        label="考试开始时间"
        width="180">
      </el-table-column>

      <el-table-column
        prop="exam.examLastTime"
        label="考试结束时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="score"
        label="考试成绩"
        width="180">
      </el-table-column>

      <el-table-column
        label="批阅状态"
        width="180">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==0" type="danger">未批阅</el-tag>
          <el-tag v-if="scope.row.status==1" type="success">已批阅</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        label="操作"
        width="180">
      <template slot-scope="scope">
        <router-link target="_blank" :to="{path:'/check',query:{id:scope.row.exam.examPaperId, token:Token, examId:scope.row.exam.examId}}">
          <el-tag  type="" effect="dark">查看</el-tag>
        </router-link>
      </template>
      </el-table-column>
    </el-table>
  </el-card>
    </div>
  </div>
</template>

<script>
import examApi from "@/api/student/examanswer"
export default {
  data(){
    return {
      ExamScoreList:[],
      Token:""
    }
  },
  methods:{
    getExamScoreList(){
      examApi.getMyExamScore().then(r => {
        this.ExamScoreList = r.data
      })
    }
  },
  created(){
    this.getExamScoreList();
    this.Token = this.$store.state.token
  }
}
</script>

<style scoped>
.myExam{
  width: 100%;
}
.myExamList{
  width: 78%;
  margin: 0 auto;
  margin-top: 40px;
}
</style>