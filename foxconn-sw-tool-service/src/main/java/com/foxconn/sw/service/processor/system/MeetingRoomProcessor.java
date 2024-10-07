package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.service.processor.MeetingRoomConfig;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeetingRoomProcessor {

    public List<OptionsVo> rooms() {
        return MeetingRoomConfig.rooms();
    }
}
