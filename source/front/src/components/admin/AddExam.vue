<template>
    <div>
    <h1>添加考试</h1>
    <el-form ref="form" :model="examModel" label-width="80px" style="margin-top: 10px;">

<el-form-item label="考试名称" required>
    <el-input v-model="examModel.examName"   style="width:80%"/>
</el-form-item>

<el-form-item label="时间限制" required >
        <el-date-picker v-model="examModel.limitDateTime" value-format="yyyy-MM-dd HH:mm:ss" type="datetimerange" @change="change"
                        range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
        </el-date-picker>
</el-form-item>
    
<el-descriptions title="试卷信息" v-if=" examModel.selectedPaper!=null">
    <el-descriptions-item label="试卷ID">{{ examModel.selectedPaper.paperId }}</el-descriptions-item>
    <el-descriptions-item label="试卷名称">{{ examModel.selectedPaper.paperName }}</el-descriptions-item>
</el-descriptions>

<el-form-item> <el-button type="primary" size="mini" class="question-item-remove" icon="el-icon-plus"  @click="selectPaper" >选择试卷</el-button></el-form-item>


<el-form-item> <el-button type="success" size="mini" class="question-item-remove" icon="el-icon-plus"  @click="createExam">创建考试</el-button> </el-form-item>
</el-form>

<el-dialog :visible.sync="paperPage.showDialog"  width="70%">

<el-form :model="paperPage.queryParam" ref="queryForm" :inline="true">

  <el-form-item label="ID：">
    <el-input v-model="paperPage.queryParam.paperId"  clearable></el-input>
  </el-form-item>

  <el-form-item label="试卷名称：">
    <el-input v-model="paperPage.queryParam.paperName"  clearable></el-input>
  </el-form-item>
  

  <el-form-item>
    <el-button type="primary" @click="queryForm">查询</el-button>
  </el-form-item>
</el-form>

<el-table  :data="paperPage.tableData"
@current-change="handleCurrentChange"
          border fit highlight-current-row style="width: 100%">
  <el-table-column prop="paperId" label="试卷ID" width="100px"/>
  <el-table-column prop="paperName" label="试卷名字"  width="100px"/>
  <el-table-column prop="article" label="试卷说明" show-overflow-tooltip/>
</el-table>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChangepage"
      :current-page="paperPage.queryParam.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="paperPage.queryParam.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="paperPage.total"
    >
    </el-pagination>
<span slot="footer" class="dialog-footer">
    <el-button @click="paperPage.showDialog = false">取 消</el-button>
    <el-button type="primary"  @click="comfirmSelectPaper">确定</el-button>
</span>
</el-dialog>
</div>
</template>

<script>
import examApi from "@/api/admin/exam_management"
import paperApi from "@/api/admin/paper_management"
export default {
    data(){
        return {
            examModel : {
                examId:"",
                examName:"",
                limitDateTime:[],
                selectedPaper:null
            },
            paperPage: {
            singleSelection : null,
            showDialog: false,
            queryParam: {
            paperId: null,
            paperName: null,
            pageNo: 1,
            pageSize: 5
         },
            tableData: [],
            total: 0
      },
        }
    },
    methods:{
        change(){
            console.log(this.examModel.limitDateTime);
        },
        queryForm(){
          this.search();
        },
        handleCurrentChangepage(){

        },
        handleCurrentChange(val){
            this.paperPage.singleSelection = val
            console.log(this.paperPage.singleSelection)
        },
        handleSizeChange(){
            
        },
        selectPaper(){
            this.paperPage.showDialog = true;
            this.search();
        },
        search(){
        paperApi.getPaperList(this.paperPage.queryParam).then(res =>{
            this.paperPage.tableData = res.data.rows;
            this.paperPage.total = res.data.total;
        })
    },
    comfirmSelectPaper(){
        this.examModel.selectedPaper = this.paperPage.singleSelection;
        this.paperPage.showDialog = false;
    },
    createExam(){
      examApi.addExam(this.examModel).then(res => {
        this.$message({
                message : res.message,
                type : 'success'
              });
              this.$router.push("/examlist");
      });
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