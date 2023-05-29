import axios from "axios"
import store from '@/store'
import path from "../api/path"
import Cookies from "js-cookie"

const errorHandle = (status,info) => {
    switch(status){
        case 400:
            console.log("语义有误");
            break;
        case 401:
            console.log("服务器认证失败");
            break;
        case 403:
            console.log("服务器拒绝访问");
            break;
        case 404:
            console.log("地址错误");
            break;
        case 500:
            console.log("服务器遇到意外");
            break;
        case 502:
            console.log("服务器无响应");
            break;
        default:
            console.log(info);
            break;
    }
}


const instance = axios.create({
    timeout:5000,
    baseURL: path.baseUrl,
    
})

//设置请求发送之前的拦截器
instance.interceptors.request.use(
    config =>{
        
        if (store.state.token != "") {
            // let each request carry token
            // ['X-Token'] is a custom headers key
            // please modify it according to the actual situation
            // config.headers['X-Token'] = Cookies.get("token");
            config.headers['X-Token'] = store.state.token
          }
        //   console.log(456)
        //   console.log(123)
        //   console.log(Cookies.get("token"))
        config.headers['Content-Type']='application/json;charset=utf-8';
        return config;
    },
    error => Promise.reject(error)
)

//设置接收到响应的拦截器
instance.interceptors.response.use(
    
    response => { console.log(response);
        return response.status === 200 && response.data.code === 20000? response.data : Promise.reject(response)},
    error =>{
        const { response } = error;
        errorHandle(response.status,response.info)
    }
)

export default instance;