package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.data.dto.enums.document.*;
import com.foxconn.sw.data.dto.request.system.OptionClassParams;
import com.foxconn.sw.data.dto.response.system.OptionClassVo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetOptionsProcessor {

    public List<OptionClassVo> getOptions(List<OptionClassParams> data) {
        List<OptionClassVo> vos = new ArrayList<>();
        data.forEach(e -> {
            OptionClassVo vo = new OptionClassVo();
            vo.setOptionClassParams(e);
            vo.setOptionsVoList(getOptionsByOptionKey(e));
            vos.add(vo);
        });
        return vos;
    }

    private List<OptionsVo> getOptionsByOptionKey(OptionClassParams e) {
        List<OptionsVo> vos;
        switch (e) {
            case DocumentSupplierEnums -> vos = getSourceEnums(DocumentSupplierEnums.values());
            case MainPartEnums -> vos = getSourceEnums(MainPartEnums.values());
            case MainTypeEnums -> vos = getSourceEnums(MainTypeEnums.values());
            case SourceEnums -> vos = getSourceEnums(SourceEnums.values());
            case SubTypeEnums -> vos = getSourceEnums(SubTypeEnums.values());
            case WorkTypeEnums -> vos = getSourceEnums(WorkTypeEnums.values());
            default -> vos = Lists.newArrayList();
        }
        return vos;
    }

    private List<OptionsVo> getSourceEnums(IUniverseCode[] values) {
        return Arrays.stream(values).map(e -> {
            OptionsVo vo = new OptionsVo();
            vo.setText(e.getCode());
            vo.setKey(e.getCode());
            return vo;
        }).collect(Collectors.toList());
    }
}
