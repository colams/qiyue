package com.foxconn.sw.business;

import com.foxconn.sw.data.dto.entity.oa.CapexParamsVo;
import com.foxconn.sw.data.entity.SwCapexSet;
import com.foxconn.sw.data.entity.SwCapexSetExample;
import com.foxconn.sw.data.mapper.extension.SwCapexSetExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwCapexSetBusiness {


    @Autowired
    SwCapexSetExtMapper capexSetExtMapper;


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
}
