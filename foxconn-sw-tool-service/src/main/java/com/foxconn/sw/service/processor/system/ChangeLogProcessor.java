package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.data.dto.entity.ChangeLogVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeLogProcessor {


    public List<ChangeLogVo> list() {
        List<ChangeLogVo> vos = new ArrayList<>();
        return vos;
    }
}
