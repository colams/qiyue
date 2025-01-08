package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailSpareBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateRowParams;
import com.foxconn.sw.data.dto.request.collaboration.cell.CellVo;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailSpare;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CollaborationUpdateRowProcessor {

    @Autowired
    CollaborationDetailBusiness collaborationDetailBusiness;
    @Autowired
    CollaborationDetailSpareBusiness collaborationDetailSpareBusiness;

    public Boolean createRow(CollaborationUpdateRowParams data) {
        List<SwCollaborationDetail> detailList = collaborationDetailBusiness.
                selectCollaborationsByTaskID(data.getTaskID().longValue());

        if (CollectionUtils.isEmpty(detailList)) {
            new BizException(4, "max 處理失敗，若重試仍失敗，請聯繫開發人員");
        }

        for (CellVo cellVo : data.getCellVoList()) {
            SwCollaborationDetail insertDetail = new SwCollaborationDetail();
            insertDetail.setScuId(detailList.get(0).getScuId());
            insertDetail.setRowIndex(data.getRowIndex() + 1);
            insertDetail.setColIndex(cellVo.getColIndex());
            insertDetail.setItem(cellVo.getItem());
            insertDetail.setItemValue("");
            collaborationDetailBusiness.updateOrInsert(insertDetail);

            SwCollaborationDetailSpare updateDetailSpare = new SwCollaborationDetailSpare();
            updateDetailSpare.setTaskId(data.getTaskID());
            updateDetailSpare.setDetailId(insertDetail.getId());
            updateDetailSpare.setOperator(RequestContext.getEmployeeNo());
            updateDetailSpare.setValue(cellVo.getValue());
            collaborationDetailSpareBusiness.updateOrInsert(updateDetailSpare);
        }

        batchUpdateDetails(detailList, data.getRowIndex());

        return false;
    }

    private void batchUpdateDetails(List<SwCollaborationDetail> allDetailList, int rowIndex) {
        List<SwCollaborationDetail> detailBiggerRow = allDetailList
                .stream()
                .filter(e -> e.getRowIndex().compareTo(rowIndex) >= 0)
                .collect(Collectors.toList());

        List<SwCollaborationDetail> updateRowIndex = detailBiggerRow.stream().map(e -> {
            SwCollaborationDetail detail = new SwCollaborationDetail();
            detail.setId(e.getId());
            detail.setRowIndex(e.getRowIndex() * 2 - rowIndex + 2);
            return detail;
        }).collect(Collectors.toList());
        collaborationDetailBusiness.batchUpdate(updateRowIndex);
    }
}
