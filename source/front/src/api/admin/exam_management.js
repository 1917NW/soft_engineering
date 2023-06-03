
import request from "../../utils/request"

export default {
    getExamList(searchModel){
        return request({
            url : '/exam/list',
            method : 'get',
            params : {
                examName : searchModel.examName,
                examId : searchModel.examId,
                pageNo : searchModel.pageNo,
                pageSize: searchModel.pageSize,
                
            }
        })
    },
    getExamById(id){
        return request({
            url:`/exam/${id}`,
            method : 'get'
        });
    },
    deleteExamById(id){
        return request({
            url:`/exam/${id}`,
            method : 'delete'
        });
    },
    addExam(examModel){
        return request({
            url : '/exam/add',
            method : 'post',
            data : {
                examId:examModel.examId,
                examName:examModel.examName,
                examStartTime:examModel.limitDateTime[0],
                examLastTime:examModel.limitDateTime[1],
                examPaperId:examModel.selectedPaper.paperId
            }
        });
    },
    updateExam(examModel){
        return request({
            url: '/exam',
            method:'put',
            data : {
                examId:examModel.examId,
                examName:examModel.examName,
                examStartTime:examModel.limitDateTime[0],
                examLastTime:examModel.limitDateTime[1],
                examPaperId:examModel.selectedExamId
            }
        });
    },
    saveExam(exam){
        if(exam.examId == null && exam.examId == undefined){
            return this.addUser(exam);
        }
        return this.updateUser(exam);
    },
}