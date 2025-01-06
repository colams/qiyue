package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationItemValue;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailSpare;

import java.util.Optional;

public class CollaborationDetailMapper {

    public static CollaborationItemValue CollaborationDetail2ItemValue(SwCollaborationDetail detail,
                                                                       SwCollaborationDetailSpare detailSpare) {
        CollaborationItemValue itemValue = new CollaborationItemValue();
        itemValue.setCurrentValue(detail.getItemValue());
        itemValue.setSpareValue(Optional.ofNullable(detailSpare).map(e -> e.getValue()).orElse(""));
        String createTime = DateTimeUtils.format(detail.getCreateTime());
        String updateTime = DateTimeUtils.format(detail.getDatetimeLastchange());
        if (!createTime.equalsIgnoreCase(updateTime)) {
            itemValue.setUpdateTime(updateTime);
        }
        itemValue.setDetailId(detail.getId());
        itemValue.setRowIndex(detail.getRowIndex());
        itemValue.setColIndex(detail.getColIndex());
        itemValue.setAuthorized("");    // todo
        return itemValue;
    }

}
