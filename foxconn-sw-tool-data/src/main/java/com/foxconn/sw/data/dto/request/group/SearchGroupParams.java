package com.foxconn.sw.data.dto.request.group;

import com.foxconn.sw.data.dto.entity.OrderByVo;

public class SearchGroupParams {

    private String keywords;
    /**
     * 0- 公开群组，1-我的收藏，2-我的群组
     */
    private Integer searchType;

    private OrderByVo orderByVo;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public OrderByVo getOrderByVo() {
        return orderByVo;
    }

    public void setOrderByVo(OrderByVo orderByVo) {
        this.orderByVo = orderByVo;
    }
}
