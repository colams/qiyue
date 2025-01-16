package com.foxconn.sw.business.report;

import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.entity.SwWorkReportLock;
import com.foxconn.sw.data.mapper.extension.oa.SwWorkReportLockExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SwWorkReportLockBusiness {

    @Autowired
    SwWorkReportLockExtensionMapper reportLockExtensionMapper;

    /**
     * 获取锁定信息
     *
     * @param yearWeek
     * @return
     */
    public boolean queryWorkReportLock(String yearWeek) {
        SwWorkReportLock reportLock = reportLockExtensionMapper.queryByYearWeek(yearWeek);
        return Objects.nonNull(reportLock) && reportLock.getLockStatus() == 0;
    }

    /**
     * 获取锁定信息
     *
     * @param yearWeek
     * @return
     */
    public boolean updateLockStatusYearWeek(String yearWeek) {
        SwWorkReportLock reportLock = reportLockExtensionMapper.queryByYearWeek(yearWeek);
        if (Objects.isNull(reportLock)) {
            SwWorkReportLock entity = new SwWorkReportLock();
            entity.setLockStatus(0);
            entity.setYearWeek(yearWeek);
            entity.setOperator(RequestContext.getNameEmployeeNo());
            return reportLockExtensionMapper.insertSelective(entity) > 0;
        } else {
            SwWorkReportLock updateEntity = new SwWorkReportLock();
            updateEntity.setId(reportLock.getId());
            updateEntity.setLockStatus(reportLock.getLockStatus() == 1 ? 0 : 1);
            return reportLockExtensionMapper.updateByPrimaryKeySelective(updateEntity) > 0;
        }
    }


}
