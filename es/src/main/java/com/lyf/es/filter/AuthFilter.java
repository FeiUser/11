package com.lyf.es.filter;


import com.lyf.es.exception.InvalidTokenException;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器
 */
@WebFilter(urlPatterns = "/*")
@Configuration
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 不走过滤器
        if (httpServletRequest.getServletPath().contains("/login/userlogin")) {
            chain.doFilter(httpServletRequest, httpResponse);
            return;
        }
        if ("OPTIONS".equals(httpServletRequest.getMethod())) {
            chain.doFilter(httpServletRequest, httpResponse);
            return;
        }

        // 登录token
        String token = httpServletRequest.getHeader("token");
        try {
//            Claims claims = JWT.parseJWT(token, "7786df7fc3a34e26a61c034d5ec8245d");
//            JSONObject subject = JSONObject.fromObject(claims.getSubject());
//
//            SysUserInfo userinfo = (SysUserInfo) JSONObject.toBean(subject, SysUserInfo.class);
//            httpServletRequest.setAttribute("userinfo", userinfo);

            chain.doFilter(httpServletRequest, response);
        } catch (Exception e) {
            //token过期，token不合法
            e.printStackTrace();
            throw new InvalidTokenException();
        }
        System.out.println("end doFilter");
    }

    @Override
    public void destroy() {

    }
}