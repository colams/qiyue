package com.foxconn.sw.business.report;

import com.foxconn.sw.data.dto.request.report.ScoreParams;
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

    public SwWorkReportScore queryScore(String employeeNo, String yearWeek) {
        SwWorkReportScoreExample example = new SwWorkReportScoreExample();
        SwWorkReportScoreExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNoEqualTo(employeeNo);
        criteria.andYearWeekEqualTo(yearWeek);
        List<SwWorkReportScore> scores = scoreExtensionMapper.selectByExample(example);
        return scores.stream().findFirst().orElse(null);
    }

    public boolean updateScore(ScoreParams data, SwWorkReportScore score) {
        SwWorkReportScore updateScore = new SwWorkReportScore();
        updateScore.setId(score.getId());
        updateScore.setScore(data.getScore());
        return scoreExtensionMapper.updateByPrimaryKeySelective(updateScore) > 0;
    }
}
