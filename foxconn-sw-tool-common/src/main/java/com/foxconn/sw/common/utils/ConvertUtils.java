package com.foxconn.sw.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertUtils {

    @Named("listIntegerToString")
    public static String listIntegerToString(List<Integer> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }
        return String.join(",", resourceIds.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    @Named("stringToListInt")
    public static List<Integer> stringToListInt(String resourceIds) {
        if (StringUtils.isEmpty(resourceIds)) {
            return Lists.newArrayList();
        }
        return Arrays.stream(resourceIds.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Named("urlPreFix")
    public static String urlPreFix(Integer id, String fileName) {
        return DomainRetrieval.getDomain() + "/api/universal/down/" + id + "/" + fileName;
    }
}
