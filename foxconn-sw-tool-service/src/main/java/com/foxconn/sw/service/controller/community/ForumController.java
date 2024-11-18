package com.foxconn.sw.service.controller.community;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.forums.PostsBriefVo;
import com.foxconn.sw.data.dto.entity.forums.PostsDetailVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.dto.request.forums.ListPostsParams;
import com.foxconn.sw.data.dto.request.forums.PostsParams;
import com.foxconn.sw.data.dto.request.forums.UpdateAttachParams;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.forums.CreatePostsProcessor;
import com.foxconn.sw.service.processor.forums.ListPostsProcessor;
import com.foxconn.sw.service.processor.forums.PostsDetailProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/forums")
public class ForumController {
    @Autowired
    CreatePostsProcessor createPostsProcessor;
    @Autowired
    ListPostsProcessor listPostsProcessor;
    @Autowired
    PostsDetailProcessor postsDetailProcessor;

    @Permission
    @Operation(summary = "查询帖子信息", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/list")
    public Response<List<PostsBriefVo>> list(@Valid @RequestBody Request<PageParams<ListPostsParams>> request) {
        List<PostsBriefVo> vos = listPostsProcessor.list(request.getData());
        return ResponseUtils.success(vos, request.getTraceId());
    }

    @Permission
    @Operation(summary = "创建帖子", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response<Boolean> create(@Valid @RequestBody Request<PostsParams> request) {
        Boolean result = createPostsProcessor.create(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }


    @Permission
    @Operation(summary = "帖子详情", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detail")
    public Response<PostsDetailVo> detail(@Valid @RequestBody Request<IntegerParams> request) {
        PostsDetailVo detailVo = postsDetailProcessor.detail(request.getData());
        return ResponseUtils.success(detailVo, request.getTraceId());
    }

    @Permission
    @Operation(summary = "邀请参与帖子", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/invite")
    public Response<Boolean> invite(@Valid @RequestBody Request<StringParams> request) {
        return ResponseUtils.success(null, request.getTraceId());
    }

    @Permission
    @Operation(summary = "收藏帖子", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/collect")
    public Response<Boolean> collect(@Valid @RequestBody Request<IntegerParams> request) {
        return ResponseUtils.success(null, request.getTraceId());
    }

    @Permission
    @Operation(summary = "更新附件资源", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateAttach")
    public Response<Boolean> updateAttach(@Valid @RequestBody Request<UpdateAttachParams> request) {
        return ResponseUtils.success(null, request.getTraceId());
    }


}
