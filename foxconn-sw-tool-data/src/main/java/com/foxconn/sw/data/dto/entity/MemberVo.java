package com.foxconn.sw.data.dto.entity;

import com.foxconn.sw.data.dto.enums.MemberType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class MemberVo {

    private MemberType memberType;
    private List<String> members;
}
