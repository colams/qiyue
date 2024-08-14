package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.validate.ConstantsValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public class TaskParams {

    @Schema(description = "模糊查询关键词")
    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String keyWord;

    @Schema(description = "请求分类信息：0-不限，1-MIL信息，2-进行中，3-新需求，4-待验收，5-逾期")
    @ConstantsValue(intValues = {0, 1, 2, 3, 4, 5}, message = "请求类型不存在！")
    private Integer searchType;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }
}
