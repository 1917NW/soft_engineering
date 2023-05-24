<template>
    <div>
        <h1 class="title">{{ paper.paperName }}</h1>
        <div v-for="module,index in paper.paperQuestions" :key="index">
            <div>{{ module.moduleName }}</div>
            <div>{{ module.questionType }}</div>
            <div v-for="q, index2 in module.questions" :key="index2">
                <div>{{ q.questionPrefix }}</div>
                <questionShow :question="q.question"></questionShow>
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