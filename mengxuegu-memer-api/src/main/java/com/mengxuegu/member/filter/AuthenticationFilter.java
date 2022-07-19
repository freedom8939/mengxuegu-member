package com.mengxuegu.member.filter;

import com.mengxuegu.member.base.Result;
import com.mengxuegu.member.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器
 */

@Component
public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isLogin = false;
        //请求头带上令牌 Authorization:  Bearer jwtToken
        final String authHeader = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authHeader)
                && authHeader.startsWith("Bearer ")) {
            //截取token
            final String token = authHeader.substring(7);
            //解析token
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                // 是否登录
                Boolean b = (Boolean) claims.get("isLogin");
                if (b) {
                    //放行请求
                    isLogin =  true;
                }
            }
        }
        if(!isLogin){
            //未登录，就响应信息
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("未通过认证，请先进行登录");
        }

        return isLogin;
    }


}
