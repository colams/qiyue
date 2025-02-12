package com.foxconn.sw.business;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.constanst.CapexSetConstants;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.oa.CapexParamsVo;
import com.foxconn.sw.data.dto.entity.oa.CapexSetDetail2Vo;
import com.foxconn.sw.data.dto.entity.oa.CapexSetValueVo;
import com.foxconn.sw.data.dto.entity.oa.CapexSetVo;
import com.foxconn.sw.data.entity.SwCapexSet;
import com.foxconn.sw.data.entity.SwCapexSetExample;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.mapper.extension.SwCapexSetExtMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SwCapexSetBusiness {


    @Autowired
    SwCapexSetExtMapper capexSetExtMapper;
    @Autowired
    EmployeeBusiness employeeBusiness;


    public boolean insertSet(int taskID, List<CapexParamsVo> capexParamsVos) {
        deleteSet(taskID);

        capexParamsVos.forEach(e -> {
            e.getCapexSet().forEach(f -> {
                f.getCapexSets().forEach(set -> {
                    SwCapexSet capexSet = new SwCapexSet();
                    capexSet.setTaskId(taskID);
                    capexSet.setSheetName(e.getSheetName());
                    capexSet.setType(f.getType());
                    capexSet.setNumber(set.getIndex());
                    capexSet.setSetValue(set.getSetValue());
                    capexSet.setSetValueJson(JsonUtils.serialize(set.getSetValueVos()));
                    capexSetExtMapper.insertSelective(capexSet);
                });
            });
        });
        return true;
    }

    public boolean deleteSet(Integer taskID) {
        SwCapexSetExample example = new SwCapexSetExample();
        SwCapexSetExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        return capexSetExtMapper.deleteByExample(example) > 0;
    }

    public List<CapexParamsVo> queryCapexParams(Integer taskID) {
        List<CapexParamsVo> capexParamsVos = new ArrayList<>();
        List<SwCapexSet> capexSets = queryCapexSet(taskID);
        Map<String, List<SwCapexSet>> maps = capexSets.stream().collect(Collectors.groupingBy(SwCapexSet::getSheetName));
        for (Map.Entry<String, List<SwCapexSet>> entry : maps.entrySet()) {
            List<CapexSetDetail2Vo> capexSetVos = new ArrayList<>();
            CapexParamsVo vo = new CapexParamsVo();

            vo.setSheetName(entry.getKey());
            vo.setDetail2Vos(capexSetVos);

            entry.getValue().forEach(e -> {
                CapexSetDetail2Vo capexSetDetail2Vo = new CapexSetDetail2Vo();
                capexSetDetail2Vo.setIndex(e.getNumber());
                capexSetDetail2Vo.setType(e.getType());
                capexSetDetail2Vo.setSetValue(e.getSetValue());
                if (!CapexSetConstants.EMPTY.equalsIgnoreCase(e.getSetValue())
                        && !CapexSetConstants.FIXED.equalsIgnoreCase(e.getSetValue())) {
                    SwEmployee ee = employeeBusiness.selectEmployeeByENo(e.getSetValue());
                    capexSetDetail2Vo.setExtra(Optional.ofNullable(ee).map(f -> f.getName()).orElse(e.getSetValue()));
                } else {
                    capexSetDetail2Vo.setExtra("唯讀");
                }
                if (StringUtils.isNotEmpty(e.getSetValueJson())) {
                    capexSetDetail2Vo.setSetValueVos(JsonUtils.deserialize(e.getSetValueJson(), List.class, CapexSetValueVo.class));
                }
                capexSetVos.add(capexSetDetail2Vo);
            });
            capexParamsVos.add(vo);
        }
        return capexParamsVos;
    }

    public List<CapexParamsVo> queryCapexParamsOrigin(Integer taskID) {
        List<CapexParamsVo> capexParamsVos = new ArrayList<>();
        List<SwCapexSet> capexSets = queryCapexSet(taskID);
        Map<String, List<SwCapexSet>> maps = capexSets.stream().collect(Collectors.groupingBy(SwCapexSet::getSheetName));
        for (Map.Entry<String, List<SwCapexSet>> entry : maps.entrySet()) {
            List<CapexSetVo> capexSetVos = new ArrayList<>();
            CapexParamsVo vo = new CapexParamsVo();

            vo.setSheetName(entry.getKey());
            vo.setCapexSet(capexSetVos);
            Map<String, List<SwCapexSet>> typeMaps = capexSets.stream().collect(Collectors.groupingBy(SwCapexSet::getType));
            for (Map.Entry<String, List<SwCapexSet>> tEntry : typeMaps.entrySet()) {
                CapexSetVo capexSetVo = new CapexSetVo();
                List<CapexSetDetail2Vo> capexSetDetail2Vos = new ArrayList<>();
                capexSetVo.setType(tEntry.getKey());
                capexSetVo.setCapexSets(capexSetDetail2Vos);
                capexSetVos.add(capexSetVo);

                tEntry.getValue().forEach(e -> {
                    CapexSetDetail2Vo capexSetDetail2Vo = new CapexSetDetail2Vo();
                    capexSetDetail2Vo.setIndex(e.getNumber());
                    capexSetDetail2Vo.setType(tEntry.getKey());
                    capexSetDetail2Vo.setSetValue(e.getSetValue());
                    capexSetDetail2Vos.add(capexSetDetail2Vo);
                });

            }
            capexParamsVos.add(vo);
        }
        return capexParamsVos;
    }

    public List<SwCapexSet> queryCapexSet(Integer taskID) {
        SwCapexSetExample example = new SwCapexSetExample();
        SwCapexSetExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        criteria.andIsDeleteEqualTo(0);
        return capexSetExtMapper.selectByExample(example);
    }
}
