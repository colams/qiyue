package com.foxconn.sw.service.controller;

import com.foxconn.sw.business.AnnouncementBusiness;
import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.common.AnnouncementParams;
import com.foxconn.sw.data.dto.entity.common.IntegerParams;
import com.foxconn.sw.data.dto.entity.common.SwAnnouncementDto;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementBusiness announcementBusiness;

    @Operation(summary = "通知公告list", tags = TagsConstants.ANNOUNCEMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response list(@Valid @RequestBody Request<AnnouncementParams> request) {
        List<SwAnnouncementDto> swAnnouncements = announcementBusiness.queryAnnouncements(request.getData());
        return ResponseUtils.success(swAnnouncements, request.getTraceId());
    }

    @Operation(summary = "通知公告详情信息", tags = TagsConstants.ANNOUNCEMENT)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/content")
    public Response content(@Valid @RequestBody Request<IntegerParams> request) {
        SwAnnouncementDto swAnnouncements = announcementBusiness.detailAnnouncement(request.getData());
        return ResponseUtils.success(swAnnouncements, request.getTraceId());
    }

}
