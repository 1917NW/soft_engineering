<template>
  <div id="app">
    
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

#searchBox .el-input {
  width: 200px;
  margin-right: 10px;
}

#List{
    margin-top: 10px;
}

.question-item-content-input {
  margin-left: 8px;
  width: 60%;
}

.line{
    text-decoration: none;
    color : skyblue
}
.line:hover{
    color: blue;
}

.el-input{
  white-space: pre-wrap;
}
</style>
