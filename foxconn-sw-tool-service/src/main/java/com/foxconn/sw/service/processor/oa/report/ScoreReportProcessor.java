package com.foxconn.sw.service.processor.oa.report;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.report.SwWorkReportScoreBusiness;
import com.foxconn.sw.data.dto.request.report.ScoreParams;
import com.foxconn.sw.data.entity.SwWorkReportScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreReportProcessor {

    @Autowired
    SwWorkReportScoreBusiness scoreBusiness;


    public boolean score(ScoreParams data) {
        SwWorkReportScore reportScore = new SwWorkReportScore();
        reportScore.setEmployeeNo(data.getEmployeeNo());
        reportScore.setYearWeek(data.getYearWeek());
        reportScore.setScore(data.getScore());
        reportScore.setOperator(RequestContext.getNameEmployeeNo());
        return scoreBusiness.insertScore(reportScore);
    }
}
