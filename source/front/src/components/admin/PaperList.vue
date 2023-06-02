<template>
    <div>
    <el-card id="searchBox">
      <el-row>
        <el-col :span="20" align="left"
          ><el-input
            placeholder="试卷id"
            clearable
            v-model="searchModel.paperId"
          ></el-input>
          <el-input
            placeholder="试卷名字"
            clearable
            v-model="searchModel.paperName"
          ></el-input>
         
          <el-button
            type="primary"
            round
            icon="el-icon-search"
            @click="getPaperList"
            >搜索</el-button
          >
          <el-button
            type="primary"
            round
            icon="el-icon-plus"
            @click="addPaper"
            >添加试卷</el-button
          >
        </el-col>
        
      </el-row>
    </el-card>

    <el-card id="List">
      <el-table :data="paperList" style="width: 100%">
      

        <el-table-column prop="paperId" label="试卷ID" width="180">
          
        </el-table-column>

        <el-table-column prop="paperName" label="试卷名字" width="180">
        </el-table-column>

        <el-table-column label="操作" width="180">
          <template slot-scope = "scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="editPaper(scope.row)"></el-button>
           <el-button type="danger" icon="el-icon-delete" circle @click="deletePaper(scope.row)"></el-button>
          </template>
          
           </el-table-column>

           <el-table-column label="操作" width="180">
          <template slot-scope = "scope">
           <el-button type="success" icon="el-icon-view" circle @click="viewshow(scope.row)"></el-button>
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
        <editpaper :paper-model="currentPaper" @fatherMethod="fatherMethod"></editpaper>
    </el-dialog>

    <el-dialog ref="dialog"   :visible.sync="paperViewShow">
        <paperShow :paper="currentPaper" ></paperShow>
    </el-dialog>
</div>
</template>

<script>
import paperApi from "@/api/admin/paper_management"
import editpaper from "@/components/admin/EditPaper.vue"
import paperShow from "@/components/paper/PaperShow.vue"
export default {
  components:{
    editpaper, paperShow
  },
    data(){
        return {
          currentPaper : {},
          dialogFormVisible : false,
          paperViewShow: false,
          searchModel:{
            pageNo: 1,
            pageSize: 10,
            paperId:"",
            paperName:""
          },
          paperList:[],
          total: 0,
        }
    },
    methods:{
      handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getPaperList();
    },

    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getPaperList();
    },
        addPaper(){
            this.$router.push("/addpaper")
        },
        getPaperList(){
          paperApi.getPaperList(this.searchModel).then(res => {
            this.paperList = res.data.rows;
            this.total = res.data.total;
          })
        },
        deletePaper(paper){
      this.$confirm(`你确认删除试卷${paper.paperName}`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          paperApi.deletePaperById(paper.paperId).then(res => {
            this.$message({
            type: 'success',
            message: res.message
          });
          this.getPaperList();
          })
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    editPaper(paper){
      this.currentPaper = paper;
      this.dialogFormVisible = true;
    },
    fatherMethod(){
      this.dialogFormVisible = false;
    },
    viewshow(paper){
      this.currentPaper = paper;
      this.paperViewShow = true;
    }
    },
    created(){
      this.getPaperList();
    }
}
</script>

<style>
.el-pagination{
    margin-top: 10px;
    text-align: left;
}
</style>