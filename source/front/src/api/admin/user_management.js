
import request from "../../utils/request"

export default {
    getUserList(searchModel){
        return request({
            url : '/user/list',
            method : 'get',
            params : {
                pageNo : searchModel.pageNo,
                pageSize: searchModel.pageSize,
                username : searchModel.username,
                phone : searchModel.phone,
                userIdNumber:searchModel.userIdNumber,
                userRole:searchModel.userRole
            }
        })
    },
    getUserById(id){
        return request({
            url:`/user/${id}`,
            method : 'get'
        });
    },
    deleteUserById(id){
        return request({
            url:`/user/${id}`,
            method : 'delete'
        });
    },
    addUser(user){
        return request({
            url : '/user/add',
            method : 'post',
            data : user
        });
    },
    updateUser(user){
        return request({
            url: '/user',
            method:'put',
            data:user
        });
    },
    saveUser(user){
        if(user.userId == null && user.userId == undefined){
            return this.addUser(user);
        }
        return this.updateUser(user);
    },
}