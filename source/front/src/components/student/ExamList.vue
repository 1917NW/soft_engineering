<template>
  <div class="examcenter"> 
    <h1>考试中心</h1>
    <div class="examlist">
        <div class="exam" v-for="exam,index in examList">
          <h2 style="margin-top: 20px;">{{ exam.exam.examName }}</h2>
          <div style="margin-top: 20px;">{{ exam.exam.examStartTime }}</div>
          <div style="margin-top:5px">To</div>
          <div style="margin-top:5px">{{ exam.exam.examLastTime }}</div>
          <el-button v-if="exam.status == 0" type="primary" round @click="signUp(exam.exam.examId)" style="margin-top: 15px;">
            <span >报名</span>
          </el-button>

          <el-button v-else type="info" round @click="signUp(exam.exam.examId)" style="margin-top: 15px;">
            <span >已报名</span>
          </el-button>
        </div>
        
    </div>
  </div>
 
</template>

<script>
import examApi from "@/api/student/examlist.js"
export default {
      data(){
        return {
          examList : []
        }
      },
      methods:{
        getUserExamList(){
          examApi.getUserExamList().then(r=>{
         
            this.examList = r.data
          })
        },
        signUp(examId){
          examApi.addUserExam(examId).then(res => {
            this.$message({
                message : res.message,
                type : 'success'
              });
            this.getUserExamList();
          })
        }
      },
      created(){
          this.getUserExamList();
      }
}
</script>

<style scoped>
.examcenter{
  width: 100%;
}
.examlist{
  width: 60%;
  display: flex;
  flex-flow: wrap;
  margin: 0 auto;
}
.exam{
  width: 300px;
  height: 200px;
  background-color: whitesmoke;
  border-radius: 20px;
  margin-top: 30px;
  margin-right: 15px;
  margin-left:15px;
  border: 2px solid white;
  
}

.exam:hover{
  border: 2px solid skyblue;
}
</style>