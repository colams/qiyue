package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.response.meeting.MeetingMinuteDetailVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingV2Vo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingVo;
import com.foxconn.sw.data.dto.request.meeting.*;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.meeting.*;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/meet")
public class MeetingController {

    @Autowired
    EstablishProcessor establish;
    @Autowired
    ListMeetingProcessor listMeeting;
    @Autowired
    DetailMeetingProcessor detailMeeting;
    @Autowired
    DeleteProcessor deleteProcessor;
    @Autowired
    UpdateMeetingProcessor updateMeetingProcessor;
    @Autowired
    MeetMinuteProcessor meetMinuteProcessor;
    @Autowired
    MinuteDetailProcessor minuteDetailProcessor;


    @Permission
    @Operation(summary = "会议列表", tags = TagsConstants.MEET)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<MeetingVo>[]> list(@Valid @RequestBody Request<ListMeetingParams> request) {
        List[] vos = listMeeting.list(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "会议列表", tags = TagsConstants.MEET)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<MeetingVo> detail(@Valid @RequestBody Request<DetailMeetingParams> request) {
        MeetingVo vos = detailMeeting.detail(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "创建会议", tags = TagsConstants.MEET)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response create(@Valid @RequestBody Request<EstablishMeetingParams> request) {
        boolean result = establish.create(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新会议", tags = TagsConstants.MEET)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> update(@Valid @RequestBody Request<UpdateMeetingParams> request) {
        Boolean result = updateMeetingProcessor.update(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "删除会议", tags = TagsConstants.MEET)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/delete")
    public Response<Boolean> delete(@Valid @RequestBody Request<DeleteParams> request) {
        boolean isDelete = deleteProcessor.delete(request.getData());
        return ResponseUtils.success(isDelete, request.getTraceId());
    }


    @Permission
    @Operation(summary = "会议列表查询", tags = "meet.2")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/meetList")
    public Response<List<MeetingV2Vo>> meetList(@Valid @RequestBody Request<ListMeetingV2Params> request) {
        List<MeetingV2Vo> vos = listMeeting.meetList(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "会议记录总结", tags = "meet.2")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/meetMinute")
    public Response<Boolean> meetMinute(@Valid @RequestBody Request<MeetingMinuteParams> request) {
        Boolean result = meetMinuteProcessor.meetMinute(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "会议记录详情信息", tags = "meet.2")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/minuteDetail")
    public Response<MeetingMinuteDetailVo> minuteDetail(@Valid @RequestBody Request<MinuteDetailParams> request) {
        MeetingMinuteDetailVo vo = minuteDetailProcessor.minuteDetail(request.getData());
        return ResponseUtils.success(vo, request.getTraceId());
    }

}
