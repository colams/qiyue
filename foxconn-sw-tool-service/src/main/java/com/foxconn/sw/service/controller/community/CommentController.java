package com.foxconn.sw.service.controller.community;


import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.forums.PostsBriefVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.forums.CommentsParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/forums/comments")
public class CommentController {

    @Permission
    @Operation(summary = "获取帖子评论信息", tags = TagsConstants.COMMENTS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<PostsBriefVo>> list(@Valid @RequestBody Request<IntegerParams> request) {
        return ResponseUtils.success(null, request.getTraceId());
    }


    @Permission
    @Operation(summary = "提交评论帖子信息", tags = TagsConstants.COMMENTS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response<Boolean> create(@Valid @RequestBody Request<CommentsParams> request) {
        return ResponseUtils.success(null, request.getTraceId());
    }


}
