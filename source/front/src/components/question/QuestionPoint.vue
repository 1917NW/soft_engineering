<template>
    <div style="line-height:1.8;margin-top: 10px;">
         <div v-if="question.questionType == 0">
            <div class="q-title" v-html="htmlArticle"/>
         <el-form>
             <el-form-item label="作答区域" style="margin-top: 30px;">
              
              <el-card>{{ answer.statementAnswer }}</el-card>
              
             </el-form-item>
             <el-form-item label="总分">
                <el-tag type="success">{{ question.score }}</el-tag>
             </el-form-item>
             <el-form-item label="打分" style="margin-top: 30px;">
                <el-input style="width: 50px;" v-model="answer.subanswers[0].score"></el-input>
             </el-form-item>
         </el-form>
             
         </div>
 
         <div v-if="question.questionType == 1">
            
             <div v-for="(q,index) in question.subQuestions" :key="index">
                 <div>Q) {{ q.questionTitle}}</div>
                 <el-radio-group v-model="answer.subanswers[index].answer" :disabled="true">
                 <el-radio  v-for="item in q.items"  :key="item.prefix"  :label="item.prefix" >
                 <span class="question-prefix">{{item.prefix}}.</span>
                 <span v-html="item.content" class="q-item-span-content"></span>
                 </el-radio>
         </el-radio-group>
                </div>
         </div>
 
         <div v-if="question.questionType == 2">
             <div class="q-title" v-html="htmlArticle"/>
             <div>
                
                 <span v-for="statement,index in statements" :key="index">
                     <input v-if="index != 0" :value="answer.subanswers[index-1].answer"  type="text" style="width: 25px;height: 25px;text-align: center;" @change="change(index,$event)" :disabled="true">
                     {{ statement }}
                 </span>
             </div>
             <div v-for="(q,index) in question.subQuestions" :key="index">
                 <el-card style="margin-top: 20px;">
                 <span v-for="(item, index2) in q.items" :key="index2" style="margin-left: 10px;">
                     <span>  {{ item.prefix }}）</span>
                     <span>{{ item.content }}</span>
                 </span>
             </el-card>
             </div>
         </div>
 
         <div v-if="question.questionType == 3">
             <div class="q-title" v-html="htmlArticle"/>
             <div v-for="(q,index) in question.subQuestions" :key="index">
                 <div>Q : {{ q.questionTitle}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span> <label for="" > A : <input :disabled="true" v-model="answer.subanswers[index].answer"  type="text" style="width: 25px;height: 25px;text-align: center;"></label></span></div>
             </div>
         </div>
 
         <div v-if="question.questionType == 4">
             <div class="q-title" v-html="question.article"/>
             <div v-for="(q,index) in question.subQuestions" :key="index">
                 <div>Q) {{ q.questionTitle}}</div>
                 <el-radio-group v-model="answer.subanswers[index].answer" :disabled="true">
                 <el-radio  v-for="item in q.items"  :key="item.prefix"  :label="item.prefix" >
                 <span class="question-prefix">{{item.prefix}}.</span>
                 <span v-html="item.content" class="q-item-span-content"></span>
                 
                 </el-radio>
         </el-radio-group>
                </div>
         </div>
         <div v-if="question.questionType == 5">
             <div class="q-title" v-html="htmlArticle"/>
             
         <el-form>
             <el-form-item label="作答区域" style="margin-top: 30px;">
              <el-card> {{ answer.statementAnswer }}</el-card>
             </el-form-item>
             <el-form-item label="总分">
                <el-tag type="success">{{ question.score }}</el-tag>
             </el-form-item>
             <el-form-item label="打分" style="margin-top: 30px;">
                <el-input style="width: 50px;" v-model="answer.subanswers[0].score"></el-input>
             </el-form-item>
             
         </el-form>
             
         </div>
     </div>
 </template>
 
 <script>
 export default {
     props:{
         question:{},
         answers:null,
         Index : {

         }
     },
     data(){
         return {
            
             statements:[],
             htmlArticle : "",
             answer : {
             questionId : this.question.questionId,
             questionType : this.question.questionType,
             subanswers : [],
             statementAnswer :""
 
         }
         }
     },
     methods:{
         change(index,event){
             this.answer.subanswers[index-1].answer = event.currentTarget.value
         }
     },
     created(){
         // console.log(this.question)
      
         this.answer = this.answers[this.Index.number]
         this.Index.number ++ ;
         
         if(this.question.questionType == 0 || this.question.questionType == 5){
             this.htmlArticle = "&nbsp;&nbsp;&nbsp;&nbsp;"+this.question.article             
         }
                    
         else if(this.question.questionType == 2){
             this.statements = this.question.article.split("___")
         }
          if(this.question.questionType == 3){
             let article = this.question.article;
             let statements = article.split("|");
             let str = ""
             for(var i = 0; i< statements.length;i++){
                 let item = statements[i].split("_")
                 str += item[0] + ")   "+item[1]
                 str += "<br>"
             }
             this.htmlArticle = str
         }
       
 
     }
 }
 </script>
 
 <style scoped>
 
 *{
     text-align: left;
 }
 
 </style>