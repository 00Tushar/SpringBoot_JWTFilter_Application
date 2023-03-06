package com.niit.bej.filter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            outputStream.println("Authorization Header is Missing!");
            outputStream.close();
        } else {
            String jwtToken = authorizationHeader.substring("Bearer ".length());
            String username = Jwts.parser()
                    .setSigningKey("secretKey")
                    .parseClaimsJws(jwtToken)
                    .getBody()
                    .getSubject();
            httpServletRequest.setAttribute("username", username);
        }

        chain.doFilter(request, response);
    }

}
