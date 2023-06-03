<template>
    <div>
        <el-card>
            <span class="do-exam-time">
          <label>剩余时间：</label>
          <label>{{formatSeconds(remainTime)}}</label>
        </span>
        </el-card>
        <el-card style="width:80%; margin:20px auto">
        <h1>{{ paperModel.paperName }}</h1>
        <div v-for="mod, index in paperModel.paperQuestionList" :key="index">
            <h2 style="margin-top:40px">{{ mod.moduleName }}</h2>
            <div v-for="q, index2 in mod.questions" :key="index2">
                <el-card class="questionBody">
                    <h3>{{ q.questionPrefix }}</h3>
                    <QuestionEdit :question="q.question" :answers="answers"></QuestionEdit>
                </el-card>
            </div>
        </div>
    </el-card>
        <el-button @click="endExam" style="margin-top: 10px;margin-bottom: 20px;">结束考试</el-button>
    </div>
</template>

<script>
import paperApi from "@/api/admin/paper_management"
import QuestionEdit from "@/components/question/QuestionEdit.vue"
import answerApi from "@/api/student/answer"
import { formatSeconds } from '@/utils'
export default {
    components:{
        QuestionEdit
    },
    data(){
        return {
            endTime:"",
            remainTime: 0 ,
            paperModel : {
                paperName:"",
                paperQuestionList:null
            },
            paperId : "",
            examId : "",
            answers:[
                // {   questionId : 1,
                //     subanswers : [],
                //     statements : ""
                // }
            ],
            timer: null,
        }
    },
    methods:{
        formatSeconds (theTime) {
      return formatSeconds(theTime)
    }, timeReduce () {
     
        let _this = this
      this.timer = setInterval(function () {
        if (_this.remainTime <= 0) {
          _this.endExam()
        } else {
    
          --_this.remainTime
        }
      }, 1000)
    },
        endExam(){
            answerApi.addAnswer({
                examId : this.examId,
                answers : this.answers
            })
            this.$router.push("/studentMyExams")
        }
    },
    created(){
        let id = this.$route.query.id;
        this.paperId = id;
        this.examId = this.$route.query.examId
        this.$store.state.token = this.$route.query.token
        this.endTime = this.$route.query.endTime;
        var now = new Date();
        var endTime = new Date(this.endTime);
        var remainTimeMillons = endTime.getTime() - now.getTime();
        this.remainTime = remainTimeMillons/1000;
        paperApi.getPaperById(id).then(res => {
            // console.log(res)
            this.paperModel.paperName = res.data.paperName
            this.paperModel.paperQuestionList = JSON.parse(res.data.paperQuestionList)
          
        })
        this.timeReduce()
        

       

    }
}
</script>

<style>
.questionBody{
   
    width: 80%;
    margin:0 auto;
    margin-top: 20px;

}
</style>