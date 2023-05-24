

import request from "../utils/request"

export default {
    login(user){
        return request({
            url : '/user/login',
            method : 'post',
            data : user,
        }) 
    }
}