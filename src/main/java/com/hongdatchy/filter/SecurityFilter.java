package com.hongdatchy.filter;

import com.hongdatchy.security.TokenService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SecurityFiler", urlPatterns = {"/api/v2/category/*", "/api/v2/product/*"})
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String token = httpRequest.getHeader("token");
        if(token != null){
            TokenService tokenService = new TokenService();
            String username = tokenService.getUsername(token);
            if(username!= null){
                System.out.println("token validate");
                System.out.println("token: " + token);
                System.out.println("username: " + username);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
