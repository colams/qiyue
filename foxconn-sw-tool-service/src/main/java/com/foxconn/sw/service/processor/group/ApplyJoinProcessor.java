package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.business.group.SwCustomGroupApplyBusiness;
import com.foxconn.sw.business.group.SwCustomGroupFavoriteBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.group.ApplyJoinGroupParams;
import com.foxconn.sw.data.dto.request.group.ProcessApplyParams;
import com.foxconn.sw.data.entity.SwCustomGroupFavorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplyJoinProcessor {


    @Autowired
    SwCustomGroupApplyBusiness applyBusiness;

    @Autowired
    SwCustomGroupFavoriteBusiness customGroupFavoriteBusiness;

    public boolean applyJoin(ApplyJoinGroupParams data) {
        return applyBusiness.applyJoin(data.getGroupId(), data.getRemark());
    }

    public boolean process(ProcessApplyParams data) {
        return applyBusiness.processApply(data.getApplyID(), data.getAgree());
    }

    public boolean collect(IntegerParams data) {
        SwCustomGroupFavorite favorite = customGroupFavoriteBusiness.getFavoriteGroup(data.getParams());
        favorite.setIsDelete(NumberConstants.ZERO.equals(favorite.getIsDelete()) ? 1 : 0);
        return customGroupFavoriteBusiness.insertOrUpdate(favorite);
    }
}


