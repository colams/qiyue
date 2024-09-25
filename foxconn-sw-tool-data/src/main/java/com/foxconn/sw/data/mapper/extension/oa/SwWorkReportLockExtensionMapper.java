package com.foxconn.sw.data.mapper.extension.oa;

import com.foxconn.sw.data.entity.SwWorkReportLock;
import com.foxconn.sw.data.entity.SwWorkReportLockExample;
import com.foxconn.sw.data.mapper.auto.SwWorkReportLockMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public interface SwWorkReportLockExtensionMapper extends SwWorkReportLockMapper {

    default SwWorkReportLock queryByYearWeek(String yearWeek) {
        SwWorkReportLockExample example = new SwWorkReportLockExample();
        SwWorkReportLockExample.Criteria criteria = example.createCriteria();
        criteria.andYearWeekEqualTo(yearWeek);
        List<SwWorkReportLock> reportLocks = selectByExample(example);
        if (CollectionUtils.isEmpty(reportLocks)) {
            return null;
        }
        return reportLocks.get(0);
    }

}
