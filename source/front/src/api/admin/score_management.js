
import request from "../../utils/request"

export default {
    getScoreList(searchModel){
        return request({
            url : '/answer/scorelist',
            method : 'get',
            params : {
                pageNo : searchModel.pageNo,
                pageSize: searchModel.pageSize,
                examId : searchModel.examId,
                userId: searchModel.userId,
            }
        })
    },
    getQuestionById(id){
        return request({
            url:`/question/${id}`,
            method : 'get'
        });
    },
    deleteQuestionById(id){
        return request({
            url:`/question/${id}`,
            method : 'delete'
        });
    },
    addQuestion(question){
        return request({
            url : '/question/add',
            method : 'post',
            data : question
        });
    },
    updateQuestion(question){
        return request({
            url: '/question',
            method:'put',
            data: question
        });
    },
    saveQuestion(question){
        if(question.questionId == null && question.questionId == undefined){
            return this.addQuestion(question);
        }
        return this.updateQuestion(question);
    },
}