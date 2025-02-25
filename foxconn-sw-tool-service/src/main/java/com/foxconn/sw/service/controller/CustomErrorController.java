//package com.foxconn.sw.service.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.io.IOException;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//
//    private static final String ERROR_PATH = "/error";
//
//    @RequestMapping(value = ERROR_PATH, method = {RequestMethod.GET, RequestMethod.POST})
//    public void handleError(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // 获取请求方法
//        String method = request.getMethod();
//        if (HttpMethod.GET.matches(method)) {
//            // 如果是GET请求，重定向到默认错误页
//            response.sendRedirect("/error.html");
//        } else {
//            // 对于其他请求方法，这里可以根据需求添加处理逻辑
//            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//        }
//    }
//
//}
