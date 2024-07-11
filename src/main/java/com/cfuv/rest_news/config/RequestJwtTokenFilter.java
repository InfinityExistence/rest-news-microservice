package com.cfuv.rest_news.config;

import com.cfuv.rest_news.secure.JwtDecoder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class RequestJwtTokenFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder = new JwtDecoder();

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getMethod().equals("GET")) {
            String authorization = request.getHeader("Authorization");
            if (authorization != null && !authorization.isBlank()) {
                var token = jwtDecoder.getJwtToken(authorization);
                securityContextHolderStrategy.getContext()
                        .setAuthentication(token);
            }
        }
        doFilter(request, response, filterChain);
    }
}
