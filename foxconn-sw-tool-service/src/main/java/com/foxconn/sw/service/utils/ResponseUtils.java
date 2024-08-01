package com.foxconn.sw.service.utils;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.constants.enums.RetCode;
import com.foxconn.sw.data.interfaces.IResult;

public class ResponseUtils {

    public static <T> Response<T> response(T t, IResult retCode, String traceId) {
        Response<T> result = new Response();
        result.setResult(t);
        result.setMessage(retCode.getMsg());
        result.setCode(retCode.getCode());
        result.setTraceId(traceId);
        result.setTimeStamp(DateTimeUtils.getTimeStamp());
        return result;
    }

    public static Response failure(IResult retCode, String traceId) {
        Response result = new Response();
        result.setMessage(retCode.getMsg());
        result.setCode(retCode.getCode());
        result.setTraceId(traceId);
        result.setTimeStamp(DateTimeUtils.getTimeStamp());
        return result;
    }

    public static Response failure(String traceId) {
        Response result = new Response();
        result.setMessage(RetCode.FAILURE.getMsg());
        result.setCode(RetCode.FAILURE.getCode());
        result.setTraceId(traceId);
        result.setTimeStamp(DateTimeUtils.getTimeStamp());

        return result;
    }

    public static Response failure(int errorCode, String message, String traceId) {
        Response result = new Response();
        result.setMessage(message);
        result.setCode(errorCode);
        result.setTraceId(traceId);
        result.setTimeStamp(DateTimeUtils.getTimeStamp());

        return result;
    }

    public static Response success(String traceId) {
        Response result = new Response();
        result.setMessage(RetCode.SUCCESS.getMsg());
        result.setCode(RetCode.SUCCESS.getCode());
        result.setTraceId(traceId);
        result.setTimeStamp(DateTimeUtils.getTimeStamp());
        return result;
    }

    public static <T> Response success(T t, String traceId) {
        Response result = new Response();
        result.setMessage(RetCode.SUCCESS.getMsg());
        result.setCode(RetCode.SUCCESS.getCode());
        result.setTraceId(traceId);
        result.setResult(t);
        result.setTimeStamp(DateTimeUtils.getTimeStamp());
        return result;
    }

}
