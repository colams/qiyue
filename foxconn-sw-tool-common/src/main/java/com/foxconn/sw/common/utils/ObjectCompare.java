package com.foxconn.sw.common.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.util.Objects;

public class ObjectCompare {

    public static Pair<String, String> compare(Object newObj, Object oldObj, Class clazz, String field) {
        for (Field declaredField : clazz.getDeclaredFields()) {
            try {
                if (!field.equalsIgnoreCase(declaredField.getName())) {
                    continue;
                }

                declaredField.setAccessible(true);
                Object obj1 = declaredField.get(newObj);
                Object obj2 = declaredField.get(oldObj);
                if (Objects.nonNull(obj1) && !obj1.equals(obj2)) {
                    return Pair.of(obj1.toString(), obj2.toString());
                }
            } catch (IllegalAccessException e) {
//                 log.info("initCustomerDetailCount:{}", e);
            }
        }
        return null;
    }

}
