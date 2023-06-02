<template>
    
    <div style="line-height:1.8">
        <!-- 作文题 -->
        <div v-if="question.questionType == 0">
            <div class="q-title" v-html="question.article"/>
        </div>
        <!-- 听力选择题 -->
        <div v-if="question.questionType == 1">
            <div class="q-title" v-html="question.article"/>
            <div v-for="(q,index) in question.subQuestions" :key="index">
                <el-card style="margin-top:20px">
                <div>Q : {{ q.questionTitle}}</div>
                <div v-for="(item, index2) in q.items" :key="index2">
                    <span>  {{ item.prefix }}: </span>
                    <span>{{ item.content }}</span>
                </div>
                </el-card>
            </div>
        </div>
        <!-- 阅读选词题 -->
        <div v-if="question.questionType == 2">
            <div class="q-title" v-html="htmlArticle"/>
            <div v-for="(q,index) in question.subQuestions" :key="index">
                <el-card style="margin-top: 20px;">
                <span v-for="(item, index2) in q.items" :key="index2" style="margin-left: 10px;">
                    <span>  {{ item.prefix }}）</span>
                    <span>{{ item.content }}</span>
                </span>
            </el-card>
            </div>
        </div>
        <!-- 阅读选段题 -->
        <div v-if="question.questionType == 3">
            <div class="q-title" v-html="htmlArticle"/>
            <div v-for="(q,index) in question.subQuestions" :key="index">
                <div>Q : {{ q.questionTitle}}</div>
                <div v-for="(item, index2) in q.items" :key="index2">
                    <span>  {{ item.prefix }}: </span>
                    <span>{{ item.content }}</span>
                </div>
            </div>
        </div>
        <!-- 阅读选择题 -->
        <div v-if="question.questionType == 4">
            <div class="q-title" v-html="question.article"/>
            <div v-for="(q,index) in question.subQuestions" :key="index">
                <el-card style="margin-top:20px">
                <div>Q :  {{ q.questionTitle}}</div>
                <div v-for="(item, index2) in q.items" :key="index2">
                    <span>  {{ item.prefix }}: </span>
                    <span>{{ item.content }}</span>
                </div>
                </el-card>
            </div>
        </div>
        <!-- 翻译题 -->
        <div v-if="question.questionType == 5">
            <div class="q-title" v-html="question.article"/>
        </div>
    </div>

</template>

<script>
export default {
  name: 'QuestionShow',
  props: {
    question: {
    }
  },
  data(){
    return {
        htmlArticle : ""
    }
  },
  methods: {},
  created(){
    if(this.question.questionType == 2){
        let article = this.question.article;
        let statements = article.split("___");
        let count = 1;
        // console.log(statements)
        if(statements.length > 0){
            this.htmlArticle += statements[0]
        }
        for(var i = 1 ;i < statements.length;i++){
            this.htmlArticle += " <span style='text-decoration:underline'>" + "&nbsp;"+count++ +"&nbsp;" +"</span> " + statements[i];
        }
        // console.log(this.htmlArticle)
    }
    else if(this.question.questionType == 3){
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

<style>

</style>