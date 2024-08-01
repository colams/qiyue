package com.foxconn.sw.data.exception;

import com.foxconn.sw.data.interfaces.IResult;

public class BizException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(IResult result) {
        super(result.getMsg());
        this.code = result.getCode();
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BizException(IResult result, Throwable cause) {
        super(result.getMsg(), cause);
        this.code = result.getCode();
    }


}
