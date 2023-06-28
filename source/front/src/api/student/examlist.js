
import request from "../../utils/request"

export default {
    getUserExamList(){
        return request({
            url : '/userExam/userExamStatuslist',
            method : 'get',
           
        })
    },
    getMyExamList(){
        return request({
            url : '/userExam/myExams',
            method : 'get',
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