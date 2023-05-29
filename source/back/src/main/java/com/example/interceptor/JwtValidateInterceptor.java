package com.example.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.entity.User;
import com.example.utils.JwtUtil;
import com.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtValidateInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //添加这行代码，让OPTIONS请求通过
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        String token = request.getHeader("X-Token");


        if(token != null){
            try{
                User user = jwtUtil.parseToken(token, User.class);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        response.setContentType("application/json;charset=utf-8");
        Result<Object> fail = Result.fail("jwt无效，请重新登录");
        response.getWriter().write(JSON.toJSONString(fail));
        return false;
    }
}
