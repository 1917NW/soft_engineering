
import request from "../../utils/request"

export default {
    getAllUser(){
        return request({
            url : '/user/list',
            method : 'get',
        }) 
    }
}