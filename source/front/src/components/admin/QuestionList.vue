<template>
    <div>
   <el-card id="searchBox">
      <el-row>
        <el-col :span="20" align="left"
          ><el-input
            placeholder="题目id"
            clearable
            v-model="searchModel.id"
          ></el-input>

          
    <el-select v-model="searchModel.type" placeholder="题型">
      <el-option label="选择题" value="1"></el-option>
      <el-option label="作文题" value="2"></el-option>
    </el-select>
  
          
         
          <el-button
            type="primary"
            round
            icon="el-icon-search"
            @click="searchQuestion"
            >搜索</el-button
          >
          <el-button
            type="primary"
            round
            @click="addQuestion"
            icon="el-icon-plus"
           
            >添加试题</el-button
          >
        </el-col>
        
      </el-row>
    </el-card>
    <el-card id="List">
      <el-table :data="questionList" style="width: 100%">
        <el-table-column prop="questionId" label="ID" width="180">
        </el-table-column>

        <el-table-column label="题型" width="180">
          <template slot-scope="scope">
              <span v-if="scope.row.questionType==1">选择题</span>
              <span v-if="scope.row.questionType==2">作文题</span>
          </template>
        </el-table-column>

        <el-table-column prop="article" label="文章" width="180">
        </el-table-column>
        <el-table-column  label="分数" width="180">
          <template slot-scope="scope">
              <span v-if="scope.row.score==0"></span>
              <span v-else>{{ scope.row.score }}</span>
          </template>
        </el-table-column>

        <el-table-column label="操作" width="180">
          <template slot-scope = "scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="openEditUI(scope.row)"></el-button>
           <el-button type="danger" icon="el-icon-delete" circle @click="deleteQuestion(scope.row)"></el-button>
          </template>
          
           </el-table-column>
      </el-table>
    </el-card>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>

    <el-dialog ref="dialog" title="编辑问题" @close="clearForm" :visible.sync="dialogFormVisible">
      <single :questionModel="questionModel" @father = "fatherMethod"></single>
    </el-dialog>
</div>
</template>

<script>
import questionApi from "@/api/admin/question_management"
import single from "./question/editsingle.vue";
export default {
  components:{
    single
  },
    data(){
        return {
          rowcopy:{},
          questionModel:{},
          dialogFormVisible : false,
            questionList:[],
            searchModel: {
              id:"",
              type:"",
              pageNo: 1,
              pageSize: 10,
          },
          total: 0,
        }
    },
    methods:{
      searchQuestion(){
        this.getQuestionList();
      },
        addQuestion(){
            this.$router.push("/addquestion");
        },
        handleSizeChange(pageSize) {
        this.searchModel.pageSize = pageSize;
        this.getQuestionList();
      },

    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getQuestionList();
    },
    getQuestionList() {
      questionApi.getQuestionList(this.searchModel).then((response) => {
        this.questionList = response.data.rows;
        this.total = response.data.total;
      });
    },
    deleteQuestion(question){
      this.$confirm(`你确认删除用户${question.questionId}`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          questionApi.deleteQuestionById(question.questionId).then(res => {
            this.$message({
            type: 'success',
            message: res.message
          });
          this.getQuestionList();
          })
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    openEditUI(row){
      this.questionModel = row;
      this.dialogFormVisible = true;
    },
    clearForm(){
      this.getQuestionList();
    },

    fatherMethod(){
      this.getQuestionList();
      this.dialogFormVisible=false;
      console.log(123)
    }
    },
    created(){
      this.getQuestionList();
    },
    mounted(){
      this.getQuestionList();
    }

    
}
</script>

<style>
.el-pagination{
  text-align: left;
  margin-top: 10px;
}
</style>