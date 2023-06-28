
import request from "../../utils/request"

export default {
    getMyExamScore(){
        return request({
            url : '/answer/getExamsDone',
            method : 'get',
           
        })
    },
    getMyExamAnswer(examId){
        return request({
            url : '/answer/getMyAnswer',
            method : 'get',
            params : {
                examId : examId,
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
    addUserExam(examId){
        return request({
            url : '/userExam/add',
            method : 'post',
            data : examId
        });
    },
    updateExam(examModel){
        return request({
            url: '/exam',
            method:'put',
            data:examModel
        });
    },
    saveExam(exam){
        if(exam.examId == null && exam.examId == undefined){
            return this.addUser(exam);
        }
        return this.updateUser(exam);
    },
}