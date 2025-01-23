package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumBbsBusiness;
import com.foxconn.sw.business.forums.ForumBbsCommentBusiness;
import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.business.forums.ForumPostsAttachmentBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.forums.DeletePostsParams;
import com.foxconn.sw.data.dto.request.forums.UpdateAttachParams;
import com.foxconn.sw.data.dto.request.forums.UpdateStatusParams;
import com.foxconn.sw.data.entity.ForumAttachment;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumParticipant;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class UpdatePostsProcessor {

    @Autowired
    ForumPostsAttachmentBusiness postsAttachmentBusiness;

    @Autowired
    ForumBbsBusiness forumBbsBusiness;
    @Autowired
    ForumBbsCommentBusiness forumBbsCommentBusiness;
    @Autowired
    ForumParticipantBusiness participantBusiness;

    public boolean updateAttach(UpdateAttachParams data) {
        if (Objects.isNull(data.getId()) || CollectionUtils.isEmpty(data.getResourceIds())) {
            return false;
        }

        List<ForumAttachment> attachments = postsAttachmentBusiness.selectPostsAttachment(data.getId());

        if (CollectionUtils.isEmpty(attachments)) {
            return false;
        }

        attachments.forEach(e -> {
            boolean hasAttach = data.getResourceIds().stream().anyMatch(f -> f.equals(e.getResourceId()));
            if (hasAttach) {
                postsAttachmentBusiness.deletePostsAttachment(e.getId());
            }
        });
        return true;
    }

    public boolean delete(DeletePostsParams data) {
        if (NumberConstants.ONE.equals(data.getDelType())) {
            return forumBbsBusiness.delete(data.getId());
        } else if (NumberConstants.TWO.equals(data.getDelType())) {
            boolean result = forumBbsCommentBusiness.delete(data.getId());
            if (result) {
                postsAttachmentBusiness.deletePostsAttachmentByCId(data.getId());
            }
            return result;
        }
        return false;
    }

    public boolean hidden(IntegerParams data) {
        ForumParticipant participant = participantBusiness.queryParticipants(data.getParams(), RequestContext.getEmployeeNo());
        if (Objects.isNull(participant)) {
            throw new BizException(4, "成员信息查询失败，请重试");
        }
        ForumParticipant updateEntity = new ForumParticipant();
        updateEntity.setId(participant.getId());
        updateEntity.setHidden(participant.getHidden().equals(NumberConstants.ZERO) ? NumberConstants.ONE : NumberConstants.ZERO);
        return participantBusiness.update(updateEntity);
    }

    public boolean updateStatus(UpdateStatusParams data) {
        ForumBbs forumBbs = forumBbsBusiness.getForumBbs(data.getId().intValue());
        if (Objects.isNull(forumBbs)) {
            throw new BizException(4, "成员信息查询失败，请重试");
        }
        ForumBbs updateEntity = new ForumBbs();
        updateEntity.setId(forumBbs.getId());
        if (StringUtils.isNotEmpty(forumBbs.getStatus())) {
            updateEntity.setStatus("");
        } else {
            updateEntity.setStatus("F");
        }
        return forumBbsBusiness.updatePosts(forumBbs);
    }
}
