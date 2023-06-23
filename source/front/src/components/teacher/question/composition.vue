<template>
    <el-form ref="form" :model="questionModel" label-width="120px">
  
  
    <el-form-item label="作文要求说明" style="margin-top: 20px;">
      <el-input :rows="6" type="textarea" v-model="questionModel.article" style="width: 60%;"></el-input>
    </el-form-item>
    
    <el-form-item label="总分" style="margin-top: 20px;" >
      <el-input  v-model="questionModel.score" style="width: 50px;"></el-input>
    </el-form-item>

    <el-form-item label="作文解析说明" style="margin-top: 20px;">
      <el-input :rows="6" type="textarea" v-model="questionModel.analysisTotal" style="width: 60%;"></el-input>
    </el-form-item>
    
   
   
  
 
  <el-form-item> <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-plus" @click="submitbQuestion" >创建问题</el-button></el-form-item>
  
  </el-form>
  
  
  </template>
  
  <script>
  import questionApi from "@/api/admin/question_management.js";
  
  export default {
      data(){
          return{
              questionModel:{
                  questionId:null,
                  questionType:0,
                  article:"",
  
                  subQuestions:[],
                  statements:null,
                  questionCorrect:null,
                  analysisTotal:"",
                  score : ""
              }
              
          }
      },
      methods:{
          
          submitbQuestion(){
              questionApi.addQuestion(this.questionModel).then(res => {
                  this.$message({
                  message : res.message,
                  type : 'success'
                })
              });
              this.$router.push("/tquestionlist")
             
          }
      }
  }
  </script>
  
  <style scoped>
  *{
      text-align: left;
      width: auto;
  }
  
  .question-item-label{
      margin-top: 10px;
  }
  
  .question-item-remove{
      margin-left: 20px;
  }

  </style>