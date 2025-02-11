package com.foxconn.sw.data.mapper.extension.message;

import com.foxconn.sw.data.mapper.auto.SwMsgPoolMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface SwMsgPoolExtMapper extends SwMsgPoolMapper {


    void selectStatus();
}
