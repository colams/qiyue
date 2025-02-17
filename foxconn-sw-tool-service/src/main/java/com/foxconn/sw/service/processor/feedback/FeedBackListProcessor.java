package com.foxconn.sw.service.processor.feedback;

import com.foxconn.sw.business.SwFeedbackBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.common.utils.ConfigReader;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.feedback.FeedBackVo;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;
import com.foxconn.sw.data.dto.request.feedback.FeedBackConditionParams;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwFeedback;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FeedBackListProcessor {

    @Autowired
    ConfigReader configReader;
    @Autowired
    SwFeedbackBusiness feedbackBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public PageEntity list(PageParams<FeedBackConditionParams> pageParams) {
        FeedBackConditionParams params = pageParams.getParams();
        boolean hasPower = checkConfig(RequestContext.getEmployeeNo());
        List<SwFeedback> feedbacks = feedbackBusiness.queryFeedBack(hasPower, pageParams);
        Long count = feedbackBusiness.queryFeedBackCount(hasPower, pageParams);
        List<FeedBackVo> vos = map(feedbacks, hasPower);
        return new PageEntity(count, vos);
    }

    private List<FeedBackVo> map(List<SwFeedback> feedbacks, boolean hasPower) {
        if (CollectionUtils.isEmpty(feedbacks)) {
            return Lists.newArrayList();
        }

        List<FeedBackVo> list = new ArrayList<>();
        feedbacks.forEach(e -> {

            SwEmployee employee = employeeBusiness.selectEmployeeByENo(e.getEmployeeNo());

            FeedBackVo vo = new FeedBackVo();
            vo.setId(e.getId());
            vo.setEmployeeNo(e.getEmployeeNo());
            vo.setTitle(e.getTitle());
            vo.setContact(StringUtils.isEmpty(e.getContact()) ? Objects.nonNull(employee) ? StringUtils.isEmpty(employee.getLandLine()) ? employee.getInnerEmail() : employee.getLandLine() : "" : e.getContact());
            vo.setIp(e.getIp());
            vo.setCreateTime(e.getCreateTime());
            vo.setContent(e.getContent());
            vo.setRemark(e.getRemark());
            vo.setStatus(e.getStatus());
            vo.setEmployeeVo(employeeBusiness.queryEmployeeVoByEno(e.getEmployeeNo()));
            vo.setFinishTime(DateTimeUtils.formatYMD(e.getFinishTime()));
            boolean canClose = RequestContext.getEmployeeNo().equalsIgnoreCase(e.getEmployeeNo());
            List<OperateEntity> ops = Lists.newArrayList(initCompleteOp(e.getStatus(), hasPower), initCloseOp(e.getStatus(), canClose));
            vo.setOperates(ops);
            list.add(vo);
        });
        return list;
    }

    private OperateEntity initCompleteOp(Integer status, boolean hasPower) {
        OperateEntity operate = new OperateEntity();
        operate.setOperateName("完成");
        operate.setOperateType("complete");
        if (hasPower) {
            operate.setEnable(status == 0 || status == 1);
        }
        operate.setSubOperateType(2);
        return operate;
    }

    private OperateEntity initCloseOp(Integer status, boolean canClose) {
        OperateEntity operate = new OperateEntity();
        operate.setOperateName("關閉");
        operate.setOperateType("close");
        if (canClose) {
            operate.setEnable(status != 3);
        }
        operate.setSubOperateType(3);
        return operate;
    }


    private boolean checkConfig(String employeeNo) {
        String developConfig = configReader.getDevelopConfig();
        return developConfig.indexOf(employeeNo) >= 0;
    }
}
