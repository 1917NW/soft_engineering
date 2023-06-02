<template>
    <div>
        <el-card style="width:80%; margin:20px auto">
        <h1>{{ paperModel.paperName }}</h1>

        <div v-for="mod, index in paperModel.paperQuestionList" :key="index">
            <h2 style="margin-top:40px">{{ mod.moduleName }}</h2>
            <div v-for="q, index2 in mod.questions" :key="index2">
                <el-card class="questionBody">
                    <h3>{{ q.questionPrefix }}</h3>
                    <QuestionCheck v-if="flag" :question="q.question" :answers="answers" :index="Index"></QuestionCheck>
                </el-card>
            </div>
        </div>
    </el-card>
        
    </div>
</template>

<script>
import paperApi from "@/api/admin/paper_management"
import QuestionCheck from "@/components/question/QuestionCheck.vue"
import answerApi from "@/api/student/answer"
import examanswerApi from "@/api/student/examanswer"
export default {
    components:{
        QuestionCheck
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
            
            answerApi.addAnswer({
                examId : this.examId,
                answers : this.answers
            })
        }
    },
    created(){
        let id = this.$route.query.id;
        this.paperId = id;
        this.examId = this.$route.query.examId
        this.$store.state.token = this.$route.query.token
        
        paperApi.getPaperById(id).then(res => {
            // console.log(res)
            this.paperModel.paperName = res.data.paperName
            this.paperModel.paperQuestionList = JSON.parse(res.data.paperQuestionList)
         
        })
        
        examanswerApi.getMyExamAnswer(this.examId).then(res => {
            console.log("-----------")
            
            console.log(res)

            this.answers = res.data.answers
            this.flag = true;
            
        })
        
       

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