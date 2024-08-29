package com.foxconn.sw.business.mapper;

import com.foxconn.sw.data.dto.entity.oa.WorkReportVo;
import com.foxconn.sw.data.entity.SwWorkReport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SwWorkReportMapper {

    SwWorkReportMapper INSTANCE = Mappers.getMapper(SwWorkReportMapper.class);

    List<WorkReportVo> toReportVos(List<SwWorkReport> reports);

    WorkReportVo toReportVo(SwWorkReport report);

}
