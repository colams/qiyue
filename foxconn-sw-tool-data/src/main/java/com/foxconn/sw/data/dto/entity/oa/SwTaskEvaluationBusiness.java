package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.entity.SwTaskEvaluation;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskEvaluationExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwTaskEvaluationBusiness {

    @Autowired
    SwTaskEvaluationExtensionMapper taskEvaluationExtensionMapper;


    public boolean saveEvaluate(TaskEvaluateParams data, String employeeID) {
        SwTaskEvaluation swTaskEvaluation = new SwTaskEvaluation();
        swTaskEvaluation.setTaskId(data.getTaskId());
        swTaskEvaluation.setOperator(employeeID);
        swTaskEvaluation.setCompletion(data.getCompletion());
        swTaskEvaluation.setEfficiency(data.getEfficiency());
        swTaskEvaluation.setQuality(data.getQuality());
        swTaskEvaluation.setContent(data.getContent());
        return taskEvaluationExtensionMapper.insertSelective(swTaskEvaluation) > 0;
    }
}
