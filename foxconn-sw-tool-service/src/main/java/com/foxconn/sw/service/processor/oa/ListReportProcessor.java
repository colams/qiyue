package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.mapper.SwWorkReportMapper;
import com.foxconn.sw.business.oa.SwWorkReportBusiness;
import com.foxconn.sw.data.dto.entity.oa.ReportSearchParams;
import com.foxconn.sw.data.dto.entity.oa.WorkReportVo;
import com.foxconn.sw.data.entity.SwWorkReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListReportProcessor {

    @Autowired
    SwWorkReportBusiness reportBusiness;

    public List<WorkReportVo> listReport(ReportSearchParams searchParams) {
        List<SwWorkReport> reports = reportBusiness.queryReport(searchParams);
        List<WorkReportVo> vos = SwWorkReportMapper.INSTANCE.toReportVos(reports);
        return vos;
    }
}
