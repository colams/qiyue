package com.foxconn.sw.data.dto.entity.oa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class TaskParams {

    @Schema(description = "模糊查询关键词")
    @NotBlank(message = "关键词不能为空字符串")
    private String keyWord;

    @Schema(description = "请求页容量，每页数量")
    private Integer status;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
