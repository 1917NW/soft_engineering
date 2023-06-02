<template>
    <el-form ref="form" :model="questionModel" label-width="120px">
  
  
    <el-form-item label="翻译文章" style="margin-top: 20px;">
      <el-input :rows="6" type="textarea" v-model="questionModel.article" style="width: 60%;"></el-input>
    </el-form-item>

    <el-form-item label="总分" style="margin-top: 20px;" >
      <el-input  v-model="questionModel.score" style="width: 50px;"></el-input>
    </el-form-item>

    <el-form-item label="翻译解析" style="margin-top: 20px;">
      <el-input :rows="6" type="textarea" v-model="questionModel.analysisTotal" style="width: 60%;"></el-input>
    </el-form-item>
    
   
   
  
 
  <el-form-item> <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-plus" @click="submitbQuestion" >修改问题</el-button></el-form-item>
  
  </el-form>
  
  
  </template>
  
  <script>
  import questionApi from "@/api/admin/question_management.js";
  
  export default {
        props:{
            questionModel:{
                  questionId:null,
                  questionType:5,
                  article:"",
  
                  subQuestions:[],
                  statements:null,
                  questionCorrect:null,
                  analysisTotal:"",
                  score:""
              }
        },
      data(){
          return{
              
              
          }
      },
      methods:{
          addSubQuestion(){
              this.questionModel.subQuestions.push({
                  title:'',
                  items:[{
                      prefix:'A',
                      content:''
                  },
                  {
                      prefix:'B',
                      content:''
                  },
                  {
                      prefix:'C',
                      content:''
                  },
                  {
                      prefix:'D',
                      content:''
                  }
              ]
              })
          },
          addSubQuestionOption(items){
              let newLastPrefix
              if (items.length > 0) {
              let last = items[items.length - 1]
              newLastPrefix = String.fromCharCode(last.prefix.charCodeAt() + 1)
          } else {
              newLastPrefix = 'A'
          }
        items.push({ id: null, prefix: newLastPrefix, content: '' })
          },
          removeSubQuestion(index){
              this.questionModel.subQuestions.splice(index,1);
          },
          removeSubQuestionOptions(subQuestion,index){
              subQuestion.items.splice(index);
          },
          submitbQuestion(){
              questionApi.updateQuestion(this.questionModel).then(res => {
                  this.$message({
                  message : res.message,
                  type : 'success'
                })
              });
              this.$emit("father");
             
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