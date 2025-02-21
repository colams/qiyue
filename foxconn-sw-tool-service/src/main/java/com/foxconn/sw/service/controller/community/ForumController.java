package com.foxconn.sw.service.controller.community;

import com.foxconn.sw.data.constants.TagsConstants;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.forums.BbsDetailVo;
import com.foxconn.sw.data.dto.entity.forums.BbsListVo;
import com.foxconn.sw.data.dto.entity.forums.ForumsParticipantVo;
import com.foxconn.sw.data.dto.entity.forums.PostsResourceVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.forums.*;
import com.foxconn.sw.service.aspects.Permission;
import com.foxconn.sw.service.processor.forums.*;
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
@RequestMapping("/api/forums")
public class ForumController {
    @Autowired
    CreatePostsProcessor createPostsProcessor;
    @Autowired
    ListPostsProcessor listPostsProcessor;
    @Autowired
    PostsDetailProcessor postsDetailProcessor;
    @Autowired
    InvitePostsMemberProcessor invitePostsMemberProcessor;
    @Autowired
    ForumPostsCollectProcessor postsCollectProcessor;
    @Autowired
    UpdatePostsProcessor updatePostsProcessor;
    @Autowired


    @Operation(summary = "查询帖子信息", tags = "v2")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/listV2")
    public Response<PageEntity<List<BbsListVo>>> listV2(@Valid @RequestBody Request<PageParams<ListPostsParams>> request) {
        PageEntity pageEntity = listPostsProcessor.listV2(request.getData());
        return ResponseUtils.success(pageEntity, request.getTraceId());
    }

    @Operation(summary = "创建帖子", tags = "v2")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/create")
    public Response<Boolean> create(@Valid @RequestBody Request<PostsParams> request) {
        Boolean result = createPostsProcessor.create(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "获取bbs附件信息", tags = "v2")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/attachments")
    public Response<List<PostsResourceVo>> attachments(@Valid @RequestBody Request<IntegerParams> request) {
        List<PostsResourceVo> resourceVos = postsDetailProcessor.attachments(request.getData());
        return ResponseUtils.success(resourceVos, request.getTraceId());
    }

    @Operation(summary = "获取bbs成员列表", tags = "v2")
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/participants")
    public Response<List<ForumsParticipantVo>> participants(@Valid @RequestBody Request<IntegerParams> request) {
        List<ForumsParticipantVo> participants = postsDetailProcessor.participants(request.getData());
        return ResponseUtils.success(participants, request.getTraceId());
    }

    @Operation(summary = "帖子详情", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/detailV2")
    public Response<BbsDetailVo> detailV2(@Valid @RequestBody Request<IntegerParams> request) {
        BbsDetailVo detailVo = postsDetailProcessor.detailV2(request.getData());
        return ResponseUtils.success(detailVo, request.getTraceId());
    }

    @Operation(summary = "邀请参与帖子", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/invite")
    public Response<Boolean> invite(@Valid @RequestBody Request<InvitePostsMemberParams> request) {
        boolean result = invitePostsMemberProcessor.inviteMembers(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "收藏帖子", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/collect")
    public Response<Boolean> collect(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = postsCollectProcessor.collect(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "更新附件资源", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateAttach")
    public Response<Boolean> updateAttach(@Valid @RequestBody Request<UpdateAttachParams> request) {
        boolean result = updatePostsProcessor.updateAttach(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "删除帖子", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/delete")
    public Response<Boolean> delete(@Valid @RequestBody Request<DeletePostsParams> request) {
        boolean result = updatePostsProcessor.delete(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "隐藏帖子", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/hidden")
    public Response<Boolean> hidden(@Valid @RequestBody Request<IntegerParams> request) {
        boolean result = updatePostsProcessor.hidden(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "更新状态", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateStatus")
    public Response<Boolean> updateStatus(@Valid @RequestBody Request<UpdateStatusParams> request) {
        boolean result = updatePostsProcessor.updateStatus(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @Operation(summary = "更新标题信息", tags = TagsConstants.FORUMS)
    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/updateTitle")
    public Response<Boolean> updateTitle(@Valid @RequestBody Request<UpdateTitleParams> request) {
        boolean result = updatePostsProcessor.updateTitle(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }
}
