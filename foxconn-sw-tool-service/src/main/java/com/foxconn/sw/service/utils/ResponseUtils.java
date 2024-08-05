package com.foxconn.sw.service.utils;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.interfaces.IResult;

public class ResponseUtils {

    public static <T> Response<T> response(T t, IResult retCode, String traceId) {
        Response<T> result = initResponse(traceId, retCode);
        result.setResult(t);
        return result;
    }

    public static Response failure(IResult retCode, String traceId) {
        return initResponse(traceId, retCode);
    }

    public static Response failure(String traceId) {
        return initResponse(traceId, RetCode.FAILURE);
    }

    public static Response failure(Integer errorCode, String message, String traceId) {
        return initResponse(traceId, message, errorCode);
    }

    public static Response success(String traceId) {
        return initResponse(traceId, RetCode.SUCCESS);
    }

    public static <T> Response success(T t, String traceId) {
        Response result = initResponse(traceId, RetCode.SUCCESS);
        result.setResult(t);
        return result;
    }

    private static Response initResponse(String traceId, IResult retCode) {
        return initResponse(traceId, retCode.getMsg(), retCode.getCode());
    }

    private static Response initResponse(String traceId, String msg, Integer code) {
        Response result = new Response();
        result.setTimeStamp(DateTimeUtils.getTimeStamp());
        result.setTraceId(traceId);
        result.setMessage(msg);
        result.setCode(code);
        return result;
    }
}
