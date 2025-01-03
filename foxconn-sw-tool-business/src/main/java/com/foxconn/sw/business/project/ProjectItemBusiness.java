package com.foxconn.sw.business.project;

import com.foxconn.sw.data.dto.request.project.ProjectUpdateParams;
import com.foxconn.sw.data.entity.SwProjectItem;
import com.foxconn.sw.data.entity.SwProjectItemExample;
import com.foxconn.sw.data.mapper.extension.project.SwProjectItemExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectItemBusiness {

    @Autowired
    SwProjectItemExtMapper projectItemExtMapper;

    public List<SwProjectItem> queryProjectItems() {
        SwProjectItemExample example = new SwProjectItemExample();
        SwProjectItemExample.Criteria criteria = example.createCriteria();
        return projectItemExtMapper.selectByExample(example);
    }

    public boolean insertProjectItem(SwProjectItem projectItem) {
        return projectItemExtMapper.insertSelective(projectItem) > 0;
    }


    public Boolean updateProjectValue(ProjectUpdateParams params) {
        SwProjectItem projectItem = new SwProjectItem();
        projectItem.setProjectValue(params.getProjectValue());

        SwProjectItemExample example = new SwProjectItemExample();
        SwProjectItemExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(params.getProjectId());
//        criteria.andModuleTypeEqualTo(params.getModuleType());
//        criteria.andUpdateByEqualTo(params.getUpdateBy());
//        criteria.andDetailTypeEqualTo(params.getDetailType());
        criteria.andProjectItemEqualTo(params.getProjectItem());

        return projectItemExtMapper.updateByExampleSelective(projectItem, example) > 0;
    }
}
