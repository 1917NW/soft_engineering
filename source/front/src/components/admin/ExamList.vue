<template>
  <div>
    <el-card id="searchBox">
      <el-row>
        <el-col :span="20" align="left"
          ><el-input
            placeholder="考试id"
            clearable
            v-model="searchModel.examId"
          ></el-input>
          <el-input
            placeholder="考试名称"
            clearable
            v-model="searchModel.examName"
          ></el-input>
         
          <el-button
            type="primary"
            round
            icon="el-icon-search"
            @click="getExamList"
            >搜索</el-button
          >
          <el-button
            type="primary"
            round
            icon="el-icon-plus"
            @click="addExam"
            >添加考试</el-button
          >
        </el-col>
        
      </el-row>
    </el-card>

    <el-card id="List">
      <el-table :data="examList" style="width: 100%">
      

        <el-table-column prop="examId" label="考试ID" width="180">
          
        </el-table-column>

        <el-table-column prop="examName" label="考试名字" width="180">
        </el-table-column>

        <el-table-column prop="examStartTime" label="考试开始时间" width="180">
        </el-table-column>

        <el-table-column prop="examLastTime" label="考试结束时间" width="180">
        </el-table-column>

        <el-table-column label="操作" width="180">
          <template slot-scope = "scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="editExam(scope.row)"></el-button>
           <el-button type="danger" icon="el-icon-delete" circle @click="deleteExam(scope.row)"></el-button>
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

    <el-dialog ref="dialog"   :visible.sync="dialogFormVisible">
        <editexam :exam-model="currentExam"></editexam>
    </el-dialog>
    
  </div>
  
</template>

<script>
import editexam from "@/components/admin/EditExam.vue"
import examApi from "@/api/admin/exam_management"
export default {
    components:{
        editexam
    },
    data(){
        return {
          dialogFormVisible:false,
          currentExam:{},
            searchModel:{
                examId:"",
                examName:"",
                pageNo: 1,
                pageSize: 10,
            },
            examList:[],
            total: 0,
        }
    },
    methods:{
        getExamList(){
          examApi.getExamList(this.searchModel).then(r => {
            this.examList = r.data.rows;
            this.total = r.data.total;
          })
        },
        handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getExamList();
    },

    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getExamList();
    },
    addExam(){
        this.$router.push("/addexam");
    },
    deleteExam(exam){
      this.$confirm(`你确认删除考试${exam.examName}`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          examApi.deleteExamById(exam.examId).then(res => {
            this.$message({
            type: 'success',
            message: res.message
          });
          this.getExamList();
          })
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    editExam(exam){

      this.currentExam = {
        examName : exam.examName,
        examId : exam.examId,
        limitDateTime : [exam.examStartTime, exam.examStartTime],
        selectedExamId : exam.examPaperId
      }
      this.dialogFormVisible = true;
    }
    },
    created(){
      this.getExamList();
    }
}
</script>

<style>
.el-pagination{
    margin-top: 10px;
    text-align: left;
}
</style>