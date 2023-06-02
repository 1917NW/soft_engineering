<template>
    <el-form ref="form" :model="questionModel" label-width="80px">
  
  
    <el-form-item label="文章片段" style="margin-top: 20px;" v-for="para,index in paragraphs" :key="'b'+index">
        <el-input type="textarea" :rows="6"  v-model="para.para" style="width:60%;vertical-align: top" ></el-input>
        <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-delete" @click="removePara(index)"></el-button>
    </el-form-item>
   

    <el-form-item label="填空" style="margin-top: 20px;" v-for="blank,index in blanks" :key="'a'+index">
        <el-input v-model="blank.answer" style="width:50px"></el-input>
        <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-delete" @click="removeBlank(index)"></el-button>
    </el-form-item>

    <el-form-item style="margin-top:40px"> 
        <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-plus" @click="addPara" >添加段落</el-button>
        <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-plus" @click="addBlank" >添加填空</el-button>
    </el-form-item>
    <el-form-item></el-form-item>

    


    <el-form-item label="词表"  v-for="(subQuestion,index) in questionModel.subQuestions" :key="index" >
     
      <el-form-item  :label="item.prefix" :key="item.prefix"  v-for="(item,index2) in subQuestion.items"  label-width="50px" class="question-item-label">
            <el-input v-model="item.prefix"  style="width:50px" class="prefix" />
            <el-input v-model="item.content" style="width:50%;margin-left: 20px; " class="input"/>
            <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-delete" @click="removeSubQuestionOptions(subQuestion, index2)"></el-button>
      </el-form-item>
      <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-plus" @click="addSubQuestionOption(subQuestion.items)">添加选项</el-button>
  
          <el-form-item label="每题分数" style="margin-top: 20px;">
              <el-input v-model="questionModel.score" style="width:50px"></el-input>
          </el-form-item>
  
  
          <el-form-item label="解析" style="margin-top: 20px;">
              <el-input v-model="subQuestion.analysis" style="width:80%"></el-input>
          </el-form-item>
  
     </el-form-item>
  

  
  <el-form-item> <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-plus" @click="submitbQuestion" >创建问题</el-button></el-form-item>
  
  </el-form>
  
  
  </template>
  
  <script>
  import questionApi from "@/api/admin/question_management.js";
  
  export default {
      data(){
          return{
            blanks:[],
            paragraphs :[],
              questionModel:{
                  questionId:null,
                  questionType:2,
                  article:"",
  
                  subQuestions:[{
                    questionTitle:'',
                    items:[],
                    questionScore : '',
                    correct : '',
                    analysis :'',
                  }],
              corrects:[],
              questionCorrect:"",
              analysisTotal:null,
              score :""
              }
              
          }
      },
      methods:{
        addBlank(){
            this.blanks.push({
                answer:""
            })
        },removeBlank(index){
            this.blanks.splice(index,1);
        },

        addPara(){
            this.paragraphs.push({
                para : ""
            })
        },
        removePara(index){
            this.paragraphs.splice(index,1)
        },
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
            let str = ""
                if(this.paragraphs.length > 0)
                    str += this.paragraphs[0].para
                for(var i=1;i<this.paragraphs.length;i++){
                    str += "___"
                    str += this.paragraphs[i].para
                }
              this.questionModel.article = str
            for(var i =0; i<this.blanks.length;i++)
            this.questionModel.corrects.push(this.blanks[i].answer)

           
              questionApi.addQuestion(this.questionModel).then(res => {
                  this.$message({
                  message : res.message,
                  type : 'success'
                })
              });
              this.$router.push("/questionlist")
             
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