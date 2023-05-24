<template>
<div class="student">
 <div class="contain">
        <div class="item" v-for="nav,index in navs" :key="index" @click="changeRouter(index)">{{ nav }}</div>
 </div>
 <router-view style="margin-top:20px"></router-view>
</div>
</template>

<script>
export default {
    data() {
      return {
         navs : ["考试中心", "我的考试","考试成绩"],
         mapNavs : ["/studentExamList","/studentMyExams","/studentExamScore"]
      };
    },
    methods: {
        changeRouter(index){
            console.log(this.mapNavs[index]);
            this.$router.push(this.mapNavs[index],()=>{},()=>{});
        }
    },
    created(){
        this.$router.push("/studentExamList",()=>{},()=>{});
    }
}
</script>

<style scoped>
    .student{
        height: 100%;
    }
    .contain{
        display: flex;
        width: 60%;
        height: 10%;
        margin: 0 auto;
        border:1px solid black;
    }
    .item{
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center
    }
</style>