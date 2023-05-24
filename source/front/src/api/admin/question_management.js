
import request from "../../utils/request"

export default {
    getQuestionList(searchModel){
        return request({
            url : '/question/list',
            method : 'get',
            params : {
                pageNo : searchModel.pageNo,
                pageSize: searchModel.pageSize,
                questionType : searchModel.type,
                questionId: searchModel.id,
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
            data:question
        });
    },
    saveQuestion(question){
        if(question.questionId == null && question.questionId == undefined){
            return this.addQuestion(question);
        }
        return this.updateQuestion(question);
    },
}