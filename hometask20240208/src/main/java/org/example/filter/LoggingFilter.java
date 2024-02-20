package org.example.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Логируем детали HTTP запроса
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request Parameters: " + request.getParameterMap());
        System.out.println("Request Headers: " + request.getHeaderNames());

        // Продолжаем цепочку фильтров
        try {
            filterChain.doFilter(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Логируем детали HTTP ответа
        System.out.println("Response Status: " + response.getStatus());
        System.out.println("Response Headers: " + response.getHeaderNames());
    }
}