package com.foxconn.sw.business.report;

import com.foxconn.sw.data.entity.SwWorkReportScore;
import com.foxconn.sw.data.entity.SwWorkReportScoreExample;
import com.foxconn.sw.data.mapper.extension.SwWorkReportScoreExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwWorkReportScoreBusiness {
    @Autowired
    SwWorkReportScoreExtensionMapper scoreExtensionMapper;

    public List<SwWorkReportScore> queryScores(List<String> yearWeeks) {
        SwWorkReportScoreExample example = new SwWorkReportScoreExample();
        SwWorkReportScoreExample.Criteria criteria = example.createCriteria();
        criteria.andYearWeekIn(yearWeeks);
        return scoreExtensionMapper.selectByExample(example);
    }

    public boolean insertScore(SwWorkReportScore reportScore) {
        return scoreExtensionMapper.insertSelective(reportScore) > 1;
    }
}
