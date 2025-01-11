package com.foxconn.sw.service.controller;

import com.foxconn.sw.business.AnnouncementBusiness;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.announcement.AnnouncementListParams;
import com.foxconn.sw.data.dto.request.announcement.AnnouncementParams;
import com.foxconn.sw.data.dto.response.announcement.AnnouncementDetailVo;
import com.foxconn.sw.data.dto.response.announcement.AnnouncementListVo;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.announcement.AnnouncementDetailProcessor;
import com.foxconn.sw.service.processor.announcement.AnnouncementManagerProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementBusiness announcementBusiness;
    @Autowired
    AnnouncementManagerProcessor announcementManagerProcessor;
    @Autowired
    AnnouncementDetailProcessor announcementDetailProcessor;

    @Operation(summary = "通知公告list", tags = TagsConstants.ANNOUNCEMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response list(@Valid @RequestBody Request<AnnouncementListParams> request) {
        List<AnnouncementListVo> swAnnouncements = announcementBusiness.queryAnnouncements(request.getData());
        return ResponseUtils.success(swAnnouncements, request.getTraceId());
    }

    @Permission
    @Operation(summary = "所有更新信息列表", tags = TagsConstants.ANNOUNCEMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/save")
    public Response<Boolean> save(@Valid @RequestBody Request<AnnouncementParams> request) {
        Boolean result = announcementManagerProcessor.updateAnnouncement(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "通知公告详情信息", tags = TagsConstants.ANNOUNCEMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<AnnouncementDetailVo> detail(@Valid @RequestBody Request<IntegerParams> request) {
        AnnouncementDetailVo swAnnouncements = announcementDetailProcessor.detail(request.getData());
        return ResponseUtils.success(swAnnouncements, request.getTraceId());
    }
}
