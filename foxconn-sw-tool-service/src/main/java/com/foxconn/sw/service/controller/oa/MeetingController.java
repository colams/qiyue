package com.foxconn.sw.service.controller.oa;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.meeting.MeetingVo;
import com.foxconn.sw.data.dto.request.meeting.DeleteParams;
import com.foxconn.sw.data.dto.request.meeting.EstablishMeetingParams;
import com.foxconn.sw.data.dto.request.meeting.UpdateMeetingParams;
import com.foxconn.sw.data.dto.request.meeting.ListMeetingParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.meeting.DeleteProcessor;
import com.foxconn.sw.service.processor.meeting.EstablishProcessor;
import com.foxconn.sw.service.processor.meeting.ListMeetingProcessor;
import com.foxconn.sw.service.processor.meeting.UpdateMeetingProcessor;
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
    DeleteProcessor deleteProcessor;
    @Autowired
    UpdateMeetingProcessor updateMeetingProcessor;

    @Permission
    @Operation(summary = "会议列表", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response list(@Valid @RequestBody Request<ListMeetingParams> request) {
        List<MeetingVo> vos = listMeeting.list(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "创建会议", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response create(@Valid @RequestBody Request<EstablishMeetingParams> request) {
        boolean result = establish.create(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新会议", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/update")
    public Response<Boolean> update(@Valid @RequestBody Request<UpdateMeetingParams> request) {
        Boolean result = updateMeetingProcessor.update(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Permission
    @Operation(summary = "删除会议", tags = TagsConstants.OA)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/delete")
    public Response<Boolean> delete(@Valid @RequestBody Request<DeleteParams> request) {
        boolean isDelete = deleteProcessor.delete(request.getData());
        return ResponseUtils.success(isDelete, request.getTraceId());
    }

}
