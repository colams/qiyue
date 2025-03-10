package com.foxconn.sw.service.processor.document;

import com.foxconn.sw.data.dto.entity.system.OptionClassVo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.data.dto.enums.document.*;
import com.foxconn.sw.data.dto.request.system.OptionParams;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetOptionsProcessor {


    public List<OptionClassVo> getOptions(List<OptionParams> data) {
        List<OptionClassVo> optionClassVos = new ArrayList<>();
        data.forEach(e -> {
            OptionClassVo optionClassVo = new OptionClassVo();
            optionClassVos.add(optionClassVo);
            optionClassVo.setOptionName(e.getOptionsEnums().name());
            switch (e.getOptionsEnums()) {
                case DocumentOption -> optionClassVo.setOptionClassVos(getOptionsDoc());
                default -> optionClassVo.setOptionsVoList(Lists.newArrayList());
            }
        });
        return optionClassVos;
    }

    public List<OptionClassVo> getOptionsDoc() {
        List<OptionClassVo> vos = new ArrayList<>();
        vos.add(init(DocumentSupplierEnums.class.getSimpleName(), DocumentSupplierEnums.values()));
        vos.add(init(MainPartEnums.class.getSimpleName(), MainPartEnums.values()));
        vos.add(init(MainTypeEnums.class.getSimpleName(), MainTypeEnums.values()));
        vos.add(init(SourceEnums.class.getSimpleName(), SourceEnums.values()));
        vos.add(init(SubTypeEnums.class.getSimpleName(), SubTypeEnums.values()));
        vos.add(init(WorkTypeEnums.class.getSimpleName(), WorkTypeEnums.values()));

        vos.add(init(IssueModeEnums.class.getSimpleName(), IssueModeEnums.values()));
        vos.add(init(PhaseEnums.class.getSimpleName(), PhaseEnums.values()));
        vos.add(init(ProcessEnums.class.getSimpleName(), ProcessEnums.values()));

        return vos;
    }

    private OptionClassVo init(String simpleName, IUniverseCode[] values) {
        OptionClassVo vo = new OptionClassVo();
        vo.setOptionName(simpleName);
        vo.setOptionsVoList(Lists.newArrayList());
        for (IUniverseCode value : values) {
            OptionsVo optionsVo = new OptionsVo();
            optionsVo.setText(value.getName());
            optionsVo.setKey(value.getCode());
            if (simpleName.equalsIgnoreCase(SubTypeEnums.class.getSimpleName())) {
                optionsVo.setExtra(((SubTypeEnums) value).getMainTypeEnums().getCode());
            }
            vo.getOptionsVoList().add(optionsVo);
        }
        return vo;
    }
}
