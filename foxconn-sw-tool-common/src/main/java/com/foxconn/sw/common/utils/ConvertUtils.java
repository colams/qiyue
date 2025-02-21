package com.foxconn.sw.common.utils;

import com.foxconn.sw.common.constanst.Constants;
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
        String domain = DomainRetrieval.getDomain();
        if (DomainRetrieval.getDomain().indexOf("127.0.0.1") > 0) {
            domain = Constants.Domain;
        }
        return domain + "/api/universal/down/" + id + "/" + fileName;
    }
}
