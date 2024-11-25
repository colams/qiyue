package com.foxconn.sw.service.processor.feedback;

import com.foxconn.sw.business.SwFeedbackBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.ConfigReader;
import com.foxconn.sw.data.dto.entity.feedback.FeedBackVo;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;
import com.foxconn.sw.data.dto.request.feedback.FeedBackConditionParams;
import com.foxconn.sw.data.entity.SwFeedback;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeedBackListProcessor {

    @Autowired
    ConfigReader configReader;
    @Autowired
    SwFeedbackBusiness feedbackBusiness;

    public List<FeedBackVo> list(FeedBackConditionParams params) {
        String employeeNo = RequestContext.getEmployeeNo();
        boolean hasPower = checkConfig(employeeNo);
        if (hasPower) {
            employeeNo = "";

            if (StringUtils.isNotEmpty(params.getEmployeeNo())) {
                employeeNo = params.getEmployeeNo();
            }
        }
        List<SwFeedback> feedbacks = feedbackBusiness.queryFeedBack(employeeNo, params.getTitle(), params.getStatus());
        return map(feedbacks, hasPower);
    }

    private List<FeedBackVo> map(List<SwFeedback> feedbacks, boolean hasPower) {
        if (CollectionUtils.isEmpty(feedbacks)) {
            return Lists.newArrayList();
        }

        List<FeedBackVo> list = new ArrayList<>();
        feedbacks.forEach(e -> {
            FeedBackVo vo = new FeedBackVo();
            vo.setId(e.getId());
            vo.setEmployeeNo(e.getEmployeeNo());
            vo.setTitle(e.getTitle());
            vo.setContact(e.getContact());
            vo.setIp(e.getIp());
            vo.setCreateTime(e.getCreateTime());
            vo.setContent(e.getContent());
            vo.setStatus(e.getStatus());
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
