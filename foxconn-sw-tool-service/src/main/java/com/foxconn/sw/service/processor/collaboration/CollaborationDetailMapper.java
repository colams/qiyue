package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationItemValue;
import com.foxconn.sw.data.entity.SwCollaborationDetail;

public class CollaborationDetailMapper {

    public static CollaborationItemValue CollaborationDetail2ItemValue(SwCollaborationDetail detail) {
        CollaborationItemValue itemValue = new CollaborationItemValue();
        itemValue.setCurrentValue(detail.getItemValue());
        itemValue.setSpareValue(detail.getSpareValue());
        itemValue.setUpdateTime(DateTimeUtils.format(detail.getDatetimeLastchange()));
        itemValue.setDetailId(detail.getId());
        itemValue.setRowIndex(detail.getRowIndex());
        itemValue.setColIndex(detail.getColIndex());
        return itemValue;
    }

}
