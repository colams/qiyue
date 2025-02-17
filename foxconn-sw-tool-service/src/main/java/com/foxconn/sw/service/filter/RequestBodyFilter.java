package com.foxconn.sw.service.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(filterName = "requestBodyFilter", urlPatterns = "/*", asyncSupported = true)
public class RequestBodyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        // 将 ServletRequest 转换为 HttpServletRequest
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        // 创建 CustomRequestWrapper 实例，将原始的 HttpServletRequest 传入
//        CustomRequestWrapper customRequestWrapper = new CustomRequestWrapper(httpServletRequest);
//        // 将 CustomRequestWrapper 传递给过滤器链继续处理
//        chain.doFilter(customRequestWrapper, response);
        chain.doFilter(request,response);
    }
}