
import request from "../../utils/request"

export default {
    getPaperList(searchModel){
        return request({
            url : '/paper/list',
            method : 'get',
            params : {
                paperName : searchModel.paperName,
                paperId : searchModel.paperId,
                pageNo : searchModel.pageNo,
                pageSize: searchModel.pageSize,
                
            }
        })
    },
    getPaperById(id){
        return request({
            url:`/paper/${id}`,
            method : 'get'
        });
    },
    deletePaperById(id){
        return request({
            url:`/paper/${id}`,
            method : 'delete'
        });
    },
    addPaper(paperModel){
        return request({
            url : '/paper/add',
            method : 'post',
            data : paperModel
        });
    },
    updatePaper(paperModel){
        return request({
            url: '/paper',
            method:'put',
            data:paperModel
        });
    },
    savePaper(paper){
        if(paper.paperId == null && paper.paperId == undefined){
            return this.addPaper(paper);
        }
        return this.updatePaper(paper);
    },
}