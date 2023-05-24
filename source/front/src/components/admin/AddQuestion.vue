<template>
    <div>
  <h1>添加试题</h1>
  <el-menu :default-active="this.$route.path" class="el-menu-demo" mode="horizontal" @select="handleSelect" router>
  <el-menu-item index="/single">听力题</el-menu-item>
  <el-menu-item index="/single">阅读题</el-menu-item>
  <el-menu-item index="/single">翻译题</el-menu-item>
  <el-menu-item index="/single">作文题</el-menu-item>
</el-menu>
<router-view></router-view>
</div>

</template>

<script>
export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style>

</style>