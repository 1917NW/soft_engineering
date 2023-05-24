<template>
    <div>
  <h1>添加试卷</h1>
  <el-form ref="form" :model="paperModel" label-width="80px" style="margin-top: 10px;">

    <el-form-item label="试卷名称" >
        <el-input v-model="paperModel.paperName"   style="width:80%"/>
    </el-form-item>
        
    <el-form-item label="模块标题"  v-for="(paperQuestion,index) in paperModel.paperQuestions" :key="index" >
        <el-input v-model="paperQuestion.moduleName"   style="width:80%"/>
        <el-form-item v-for="(questionItem,index) in paperQuestion.questions" label="问题" :key="index" style="margin-top: 10px;">
            <el-input v-model="questionItem.questionPrefix" placeholder="请输入试题标题" style="width: 60%;"></el-input>
           
            <el-button type="text" class="link-left" style="margin-left: 20px" size="mini" @click="addQuestion(questionItem)">
            添加/修改题目
            </el-button>

            <el-button type="text" class="link-left" style="margin-left: 20px" size="mini" @click="addQuestion()">
            删除题目
            </el-button>
            <el-card class="exampaper-item-box" v-if="questionItem.question!=null" style="margin-top:10px;width:72%">
                <QuestionShow :question="questionItem.question"></QuestionShow>
        </el-card>
            
        </el-form-item>
        <el-button type="danger" size="mini" class="question-item-remove" icon="el-icon-plus" @click="addSmallQuestion(paperQuestion.questions)" style="margin-top:20px">添加问题</el-button>

    </el-form-item>
    <el-form-item> <el-button type="primary" size="mini" class="question-item-remove" icon="el-icon-plus" @click="addModule" >添加模块</el-button></el-form-item>


    <el-form-item> <el-button type="success" size="mini" class="question-item-remove" icon="el-icon-plus" @click="submitPaper()" >创建试卷</el-button> </el-form-item>
  </el-form>

  <el-dialog :visible.sync="questionPage.showDialog"  width="70%">

<el-form :model="questionPage.queryParam" ref="queryForm" :inline="true">

  <el-form-item label="ID：">
    <el-input v-model="questionPage.queryParam.id"  clearable></el-input>
  </el-form-item>

  <el-form-item label="题型：">
    <el-select v-model="questionPage.queryParam.questionType" clearable>
      <el-option  value="1" label="选择题"></el-option>
    </el-select>
  </el-form-item>

  <el-form-item>
    <el-button type="primary" @click="queryForm">查询</el-button>
  </el-form-item>
</el-form>

<el-table  :data="questionPage.tableData"
@current-change="handleCurrentChange"
          border fit highlight-current-row style="width: 100%">
  <el-table-column prop="questionId" label="Id" width="60px"/>
  <el-table-column prop="questionType" label="题型"  width="70px"/>
  <el-table-column prop="article" label="文章" show-overflow-tooltip/>
</el-table>  
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChangepage"
      :current-page="questionPage.queryParam.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="questionPage.queryParam.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="questionPage.total"
    >
    </el-pagination>
<span slot="footer" class="dialog-footer">
    <el-button @click="questionPage.showDialog = false">取 消</el-button>
    <el-button type="primary" @click="confirmQuestionSelect">确定</el-button>
</span>
</el-dialog>

</div>
</template>

<script>
import questionApi from "@/api/admin/question_management"
import paperApi from "@/api/admin/paper_management"
import QuestionShow from "../question/QuestionShow.vue"
export default {
    components: { QuestionShow },
    data(){
        return {
            currentQuestion:{},
            paperModel:{
                paperId:null,
                paperName:"",
                paperQuestions:[
                {
                    moduleName:"",
                    questionType:"",
                    questions:[
                        {   questionPrefix:"",
                            question:null,
                        },
                        {
                            questionPrefix:"",
                            question:null,
                        }
                    ]
                }
            ]
        },
        questionPage: {
        multipleSelection: [],
        singleSelection : null,
        showDialog: false,
        queryParam: {
          id: null,
          questionType: null,
          pageNo: 1,
          pageSize: 5
        },
        tableData: [],
        total: 0
      },
     
    }
    },
    methods:{
        handleCurrentChangepage(){

        },
        handleCurrentChange(val){

            this.questionPage.singleSelection = val
            console.log(this.questionPage.singleSelection)
        },
        handleSizeChange(){

        },
        addSmallQuestion(items){
            items.push({
                questionPrefix:"",
                questionid:""
            })
        },
        addModule(){
            this.paperModel.paperQuestions.push( {
                    moduleName:"",
                    questionType:"",
                    questions:[
                        {   questionPrefix:"",
                            questionid:"",
                        },
                        {
                            questionPrefix:"",
                            questionid:"",
                        }
                    ]
                })
        },
        submitPaper(){
            paperApi.addPaper(this.paperModel).then(res => {
                this.$message({
                message : res.message,
                type : 'success'
              });
              this.$router.push("/paperlist");
            });
        },
        confirmQuestionSelect () {
            this.currentQuestion.question=this.questionPage.singleSelection;
        // questionApi.getQuestionById( this.questionPage.singleSelection.questionId).then(re => {
        //   this.currentQuestion.question=re.data
        // });
      this.questionPage.showDialog = false
    },
    addQuestion (question) {
        this.currentQuestion = question
        this.questionPage.showDialog = true
        this.search();
      
    },
    
    queryForm () {
      this.questionPage.queryParam.pageIndex = 1
      this.search()
    },
    search(){
        questionApi.getQuestionList(this.questionPage.queryParam).then(res =>{
            this.questionPage.tableData = res.data.rows;
            this.questionPage.total = res.data.total;
        })
    }
    }
}
</script>

<style scoped>
*{
    text-align: left;
    width: auto;
}

h1{
    text-align: center;
}
</style>