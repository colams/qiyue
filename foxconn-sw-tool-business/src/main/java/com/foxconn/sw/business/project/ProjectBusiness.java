package com.foxconn.sw.business.project;

import com.foxconn.sw.data.entity.SwProject;
import com.foxconn.sw.data.entity.SwProjectExample;
import com.foxconn.sw.data.mapper.extension.project.SwProjectExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProjectBusiness {

    @Autowired
    SwProjectExtMapper projectExtMapper;

    public List<SwProject> queryProjectList() {
        SwProjectExample example = new SwProjectExample();
        SwProjectExample.Criteria criteria = example.createCriteria();

        return projectExtMapper.selectByExample(example);
    }

    public Boolean insertOrUpdate(SwProject swProject) {
        int effectCount = 0;
        if (Objects.nonNull(swProject.getId()) && swProject.getId() > 0) {
            effectCount = projectExtMapper.updateByPrimaryKeySelective(swProject);
        } else {
            effectCount = projectExtMapper.insertSelective(swProject);
        }
        return effectCount > 0;
    }

    public Boolean insert(SwProject swProject) {
        return projectExtMapper.insertSelective(swProject) > 0;
    }
}
