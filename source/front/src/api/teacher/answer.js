
import request from "../../utils/request"

export default {
    getUserAnswerList(model){
        return request({
            url : '/answer/list',
            method : 'get',
            params : {
                examId : model.examId,
                pageNo: model.pageNo ,
                pageSize : model.pageSize
            }
           
        })
    },
    addAnswer(answersModel){
        return request({
            url : '/answer/addAnswer',
            method : 'post',
            data : answersModel
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
    updateAnswer(answersModel){
        return request({
            url: '/answer/update',
            method:'put',
            data:answersModel
        });
    },
    saveExam(exam){
        if(exam.examId == null && exam.examId == undefined){
            return this.addUser(exam);
        }
        return this.updateUser(exam);
    },
}