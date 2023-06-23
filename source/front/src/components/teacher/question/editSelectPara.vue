<template>
    <el-form ref="form" :model="questionModel" label-width="80px" style="margin-top: 30px;">
  
  
      <el-form-item label="段落" style="margin-top: 20px;" v-for="para,index in paragraphs" :key="index + 1">
        <el-input v-model="para.prefix"  style="width:50px;vertical-align: top; margin-right: 20px;margin-left: 20px;" class="prefix" />
        <el-input type="textarea" :rows="6"  v-model="para.para" style="width:60%;vertical-align: top" ></el-input>
        <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-delete" @click="removePara(index)"></el-button>
    </el-form-item>

      <el-form-item> <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-plus" @click="addPara" >添加段落</el-button></el-form-item>
  
  
    <el-form-item label="子问题"  v-for="(subQuestion,index) in questionModel.subQuestions" :key="index" >
     
      <el-input type="textarea" :rows="3" v-model="subQuestion.questionTitle"   style="width:60%"/>
      <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-delete" @click="removeSubQuestion(index)"></el-button>
  
      <el-form-item  :label="item.prefix" :key="item.prefix"  v-for="(item,index2) in subQuestion.items"  label-width="50px" class="question-item-label">
            <el-input v-model="item.prefix"  style="width:50px" class="prefix" />
            <el-input v-model="item.content" style="width:50%;margin-left: 20px; " class="input"/>
            <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-delete" @click="removeSubQuestionOptions(subQuestion, index2)"></el-button>
      </el-form-item>
     
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
    props:{
        questionModel:{
                  questionId:null,
                  questionType:3,
                  article:"",
  
                  subQuestions:[],
                  statements:null,
                  questionCorrect:null,
                  analysisTotal:null
              }
    },
      data(){
          return{
              paragraphs :[],
             
              
          }
      },
      methods:{
        addPara(){
            let newLastPrefix
            if (this.paragraphs.length > 0) {
              let last = this.paragraphs[this.paragraphs.length - 1]
              newLastPrefix = String.fromCharCode(last.prefix.charCodeAt() + 1)
           } else {
               newLastPrefix = 'A'
           }
            this.paragraphs.push({
                prefix : newLastPrefix,
                para : ""
            })
        },
        removePara(index){
            this.paragraphs.splice(index,1)
        },
          addSubQuestion(){
              this.questionModel.subQuestions.push({
                  questionTitle:'',
                  items:[],
                  questionScore : '',
                  correct : '',
                  analysis :'',
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
                let str = ""
                for(var i=0;i<this.paragraphs.length;i++){
                    str += this.paragraphs[i].prefix
                    str += "_"
                    str += this.paragraphs[i].para
                    str += "|"
                }
              this.questionModel.article = str
              questionApi.updateQuestion(this.questionModel).then(res => {
                  this.$message({
                  message : res.message,
                  type : 'success'
                })
              });
              this.$emit("father");
             
          }
      },
      created(){
        console.log(this.questionModel.article)
        var paras = this.questionModel.article.split('|')
        console.log(paras)
        for(var i= 0;i<paras.length;i++){
            var item = paras[i].split('_')
            this.paragraphs.push({
                prefix : item[0],
                para : item[1]
            }
            )
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