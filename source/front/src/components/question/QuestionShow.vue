<template>
    <!--  1.听力选择题 2.选词题 3.阅读选段题 4.阅读选择题 5.翻译题 6.作文题-->
    <div style="line-height:1.8">
        <div v-if="question.questionType == 1">
            <div class="q-title" v-html="question.article"/>
            <div v-for="(q,index) in question.subQuestions" :key="index">
                <div>Q) {{ q.questionTitle}}</div>
                <div v-for="(item, index2) in q.items" :key="index2">
                    <span>  {{ item.prefix }}: </span>
                    <span>{{ item.content }}</span>
                </div>
            </div>
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
  methods: {}
}
</script>

<style>

</style>