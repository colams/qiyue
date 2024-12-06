package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.business.group.SwCustomGroupBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.dto.request.group.SearchGroupParams;
import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GroupListProcessor {

    @Autowired
    SwCustomGroupBusiness customGroupBusiness;
    @Autowired
    EmployeeUtils employeeUtils;

    public PageEntity list(PageParams<SearchGroupParams> pageParams) {
        List<GroupBriefVo> list;
        SearchGroupParams data = pageParams.getParams();
        if (NumberConstants.TWO.equals(data.getSearchType())) {
            list = listOwnerGroup(data.getKeywords());
        } else if (NumberConstants.ONE.equals(data.getSearchType())) {
            list = listCollectGroup(data.getKeywords());
        } else {
            list = listPublicGroup(data.getKeywords());
        }
        return new PageEntity<>(0, list);
    }

    /**
     * 查詢收藏群組
     *
     * @param keywords
     * @return
     */
    private List<GroupBriefVo> listCollectGroup(String keywords) {
        return Lists.newArrayList();
    }

    /**
     * 查詢公開群組
     *
     * @param keywords
     * @return
     */
    private List<GroupBriefVo> listPublicGroup(String keywords) {
        return Lists.newArrayList();
    }


    /**
     * 查詢我的群組
     *
     * @param keywords
     * @return
     */
    private List<GroupBriefVo> listOwnerGroup(String keywords) {
        List<SwCustomGroup> list = customGroupBusiness.listOwnerGroup(keywords);
        EmployeeVo owner = employeeUtils.mapEmployee(RequestContext.getEmployeeNo());
        return Optional.ofNullable(list).orElse(Lists.newArrayList())
                .stream().map(e -> CommonUtils.map(e, owner))
                .collect(Collectors.toList());
    }


}


