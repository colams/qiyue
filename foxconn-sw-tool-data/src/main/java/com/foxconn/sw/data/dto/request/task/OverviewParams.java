package com.foxconn.sw.data.dto.request.task;

import com.foxconn.sw.data.validate.ConstantsValue;
import io.swagger.v3.oas.annotations.media.Schema;

public class OverviewParams {

    @Schema(description = "请求分类信息：0-不限，1-MIL信息，2-进行中，3-新需求，4-待验收，5-逾期")
    @ConstantsValue(intValues = {0, 1, 2, 3, 4}, message = "请求类型不存在！")
    private Integer searchType;

    @Schema(description = "请求分类信息：0-不限，1-我的任务，2-我的关注")
    @ConstantsValue(intValues = {0, 1, 2}, message = "请求类型不存在！")
    private Integer viewType;

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getViewType() {
        return viewType;
    }

    public void setViewType(Integer viewType) {
        this.viewType = viewType;
    }
}
