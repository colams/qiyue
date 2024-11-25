package com.foxconn.sw.common.context;

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


    private static Object get(String key) {
        Map<String, Object> context = contextHolder.get();
        return context != null ? context.get(key) : null;
    }

    public static void remove() {
        contextHolder.remove();
    }

    public interface ContextKey {
        String USER_INFO = "userInfo";
        String NameEmployeeNo = "nameEmployeeNo";
        String EmployeeNo = "employeeNo";
        String OperateType = "operateType";

    }
}
