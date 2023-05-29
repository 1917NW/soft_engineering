<template>
    <div>
        <el-card style="width:80%; margin:20px auto">
        <h1>{{ paperModel.paperName }}</h1>
        <div v-for="mod, index in paperModel.paperQuestionList" :key="index">
            <h2 style="margin-top:40px">{{ mod.moduleName }}</h2>
            <div v-for="q, index2 in mod.questions" :key="index2">
                <el-card class="questionBody">
                    <h3>{{ q.questionPrefix }}</h3>
                    <QuestionPoint v-if="flag" :question="q.question" :answers="answers" :index="Index"></QuestionPoint>
                </el-card>
            </div>
        </div>
    </el-card>
    <el-button @click="DoneExam" style="margin-top: 10px;">结束考试</el-button>
    </div>
</template>

<script>
import examApi from "@/api/admin/exam_management"
import paperApi from "@/api/admin/paper_management"
import QuestionPoint from "@/components/question/QuestionPoint.vue"
import answerApi from "@/api/teacher/answer"

export default {
    components:{
        QuestionPoint
    },
    data(){
        return {
            flag : false,
            Index: {
                number : 0
            },
            paperModel : {
                paperName:"",
                paperQuestionList:null
            },
            paperId : "",
            examId : "",
            userId : "",
            answers:[
                // {   questionId : 1,
                //     subanswers : [],
                //     statements : ""
                // }
            ]
        }
    },
    methods:{
        endExam(){
            console.log(111)
            console.log(this.answers)
            answerApi.addAnswer({
                examId : this.examId,
                answers : this.answers
            })
        },
        DoneExam(){
            console.log(this.answers)
            answerApi.updateAnswer({
                examId : this.examId,
                answers :this.answers,
                userId : this.userId,
                status : 1
            })
        }
    },
    created(){
        let id = this.$route.query.id;
        console.log(id)
        let answerData = JSON.parse(id)
        console.log(answerData)
        // this.paperId = id;
        this.examId = answerData.examId
        this.userId = answerData.userId
       
        this.$store.state.token = this.$route.query.token
        examApi.getExamById(this.examId).then(res => {
            this.paperId = res.data.examPaperId

            paperApi.getPaperById(this.paperId).then(res => {
            // console.log(res)
            this.paperModel.paperName = res.data.paperName
            this.paperModel.paperQuestionList = JSON.parse(res.data.paperQuestionList)
            console.log(123)
            console.log( this.paperModel.paperQuestionList)
        })
        })
        this.answers = answerData.answers
        console.log(this.answers)
        this.flag = true;
        
        // examanswerApi.getMyExamAnswer(this.examId).then(res => {
        //     console.log("-----------")
            
        //     console.log(res)

        //     this.answers = res.data.answers
        //     this.flag = true;
            
        // })
        
       

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