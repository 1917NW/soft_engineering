<template>
    <el-form ref="form" :model="questionModel" label-width="80px">
  
  
      <el-form-item label="文章" style="margin-top: 20px;">
        <el-input type="textarea" :rows="6"  v-model="questionModel.article" style="width:60%"></el-input>
      </el-form-item>
  
    <el-form-item label="子问题"  v-for="(subQuestion,index) in questionModel.subQuestions" :key="index" >
     
      <el-input type="textarea" :rows="3" v-model="subQuestion.questionTitle"   style="width:60%"/>
      <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-delete" @click="removeSubQuestion(index)"></el-button>
  
      <el-form-item  :label="item.prefix" :key="item.prefix"  v-for="(item,index2) in subQuestion.items"  label-width="50px" class="question-item-label">
            <el-input v-model="item.prefix"  style="width:50px" class="prefix" />
            <el-input v-model="item.content" style="width:50%;margin-left: 20px; " class="input"/>
            <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-delete" @click="removeSubQuestionOptions(subQuestion, index2)"></el-button>
      </el-form-item>
      <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-delete" @click="addSubQuestionOption(subQuestion.items)">添加选项</el-button>
  
          <el-form-item label="分数" style="margin-top: 20px;">
              <el-input v-model="subQuestion.questionScore" style="width:50px"></el-input>
          </el-form-item>
  
          <el-form-item label="正确答案" style="margin-top: 20px;">
              <el-input v-model="subQuestion.correct" style="width:50px"></el-input>
          </el-form-item>
  
          <el-form-item label="解析" style="margin-top: 20px;">
              <el-input v-model="subQuestion.analysis" style="width:80%"></el-input>
          </el-form-item>
  
     </el-form-item>
  
  <el-form-item> <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-plus" @click="addSubQuestion" >添加子问题</el-button></el-form-item>
  
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
                  questionType:4,
                  article:"",
  
                  subQuestions:[],
                  statements:null,
                  questionCorrect:null,
                  analysisTotal:null
              }
              
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