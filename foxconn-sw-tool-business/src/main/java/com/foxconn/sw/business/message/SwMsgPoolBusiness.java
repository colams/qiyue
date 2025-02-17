package com.foxconn.sw.business.message;

import com.foxconn.sw.data.constants.enums.MsgTypeEnums;
import com.foxconn.sw.data.entity.SwMsgPool;
import com.foxconn.sw.data.mapper.extension.message.SwMsgPoolExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwMsgPoolBusiness {

    @Autowired
    SwMsgPoolExtMapper msgPoolExtMapper;

    public boolean addMsg(MsgTypeEnums msgType, Integer taskID) {
        SwMsgPool msgPool = new SwMsgPool();
        msgPool.setMsgType(msgType.name());
        msgPool.setObjectId(taskID);
        return msgPoolExtMapper.insertSelective(msgPool) > 0;
    }

    public List<SwMsgPool> getMsgPool2Process() {
        return msgPoolExtMapper.select2Process();
    }

    public boolean closeMsg(SwMsgPool e) {
        return msgPoolExtMapper.closeMsgPool(e.getId()) > 0;
    }
}
