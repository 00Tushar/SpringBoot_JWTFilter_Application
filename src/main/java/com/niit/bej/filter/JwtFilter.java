package com.niit.bej.filter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) ;
        {
            String JwtToken = authorizationHeader.substring("Bearer ".length());
            String username = Jwts.parser()
                    .setSigningKey("secretKey")
                    .parseClaimsJws(JwtToken)
                    .getBody()
                    .getSubject();
            httpServletRequest.setAttribute("username", username);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
