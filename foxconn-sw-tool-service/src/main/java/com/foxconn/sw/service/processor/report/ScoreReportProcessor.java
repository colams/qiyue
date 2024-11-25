package com.foxconn.sw.service.processor.report;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.business.report.SwWorkReportScoreBusiness;
import com.foxconn.sw.data.dto.request.report.ScoreParams;
import com.foxconn.sw.data.entity.SwWorkReportScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ScoreReportProcessor {

    @Autowired
    SwWorkReportScoreBusiness scoreBusiness;

    public boolean score(ScoreParams data) {
        SwWorkReportScore score = scoreBusiness.queryScore(data.getEmployeeNo(), data.getYearWeek());
        if (Objects.nonNull(score)) {
            return scoreBusiness.updateScore(data, score);
        } else {
            SwWorkReportScore reportScore = new SwWorkReportScore();
            reportScore.setEmployeeNo(data.getEmployeeNo());
            reportScore.setYearWeek(data.getYearWeek());
            reportScore.setScore(data.getScore());
            reportScore.setOperator(RequestContext.getNameEmployeeNo());
            return scoreBusiness.insertScore(reportScore);
        }
    }
}
