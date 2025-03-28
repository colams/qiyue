package com.foxconn.sw.data.context;

import java.util.HashMap;
import java.util.Map;

public class RequestContext {

    private static final ThreadLocal<Map<String, Object>> contextHolder = new ThreadLocal<>();

    public static void put(String key, Object value) {
        Map<String, Object> context = contextHolder.get();
        if (context == null) {
            context = new HashMap<>();
            contextHolder.set(context);
        }
        context.put(key, value);
    }

    public static Object getUserInfo() {
        return get(ContextKey.USER_INFO);
    }

    public static String getNameEmployeeNo() {
        return (String) get(ContextKey.NameEmployeeNo);
    }

    public static String getEmployeeNo() {
        return (String) get(ContextKey.EmployeeNo);
    }

    public static String getTraceID() {
        return (String) get(ContextKey.TraceID);
    }


    private static Object get(String key) {
        Map<String, Object> context = contextHolder.get();
        return context != null ? context.get(key) : null;
    }

    public static void remove() {
        contextHolder.remove();
    }

    public static String getToken() {
        return (String) get(ContextKey.Token);
    }

    public interface ContextKey {
        String USER_INFO = "userInfo";
        String NameEmployeeNo = "nameEmployeeNo";
        String EmployeeNo = "employeeNo";
        String OperateType = "operateType";
        String TraceID = "traceId";
        String Token = "token";
    }
}
