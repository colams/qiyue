package com.foxconn.sw.common.utils;

import java.util.Optional;
import java.util.function.Function;

public class OptionalUtils {

    public static <T, U> U get(T t, Function<? super T, U> mapper, U defaultValue) {
        return Optional.ofNullable(t).map(mapper).orElse(defaultValue);
    }

    public static <T, U> U get(Optional<T> op, Function<? super T, U> mapper, U defaultValue) {
        return op.map(mapper).orElse(defaultValue);
    }

}
