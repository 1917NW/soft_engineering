<template>
    <div>
        <h1 class="title">{{ paper.paperName }}</h1>
        <div v-for="module,index in paper.paperQuestions" :key="index" style="margin-top: 10px;">
            <h2>{{ module.moduleName }}</h2>
            <div>{{ module.questionType }}</div>
            <div v-for="q, index2 in module.questions" :key="index2" style="margin-top:10px">
                <h3>{{ q.questionPrefix }}</h3>
                <questionShow :question="q.question" style="margin-top: 10px;"></questionShow>
            </div>
        </div>

    </div>
    
</template>

<script>
import questionShow from "@/components/question/QuestionShow.vue"
export default {
    components:{
        questionShow
    },
    props:{
        paper : {}
    }
}
</script>

<style scoped>
div{
    text-align: left;
}

.title{
    text-align: center;
}
</style>