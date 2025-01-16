package com.foxconn.sw.business.project;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.request.project.ProjectUpdateParams;
import com.foxconn.sw.data.entity.SwProjectItem;
import com.foxconn.sw.data.entity.SwProjectItemExample;
import com.foxconn.sw.data.mapper.extension.project.SwProjectItemExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
        SwProjectItemExample example = new SwProjectItemExample();
        SwProjectItemExample.Criteria criteria = example.createCriteria();
        criteria.andProjectItemEqualTo(params.getProjectItem());
        criteria.andProjectIdEqualTo(params.getProjectId());

        List<SwProjectItem> items = projectItemExtMapper.selectByExample(example);

        SwProjectItem newProjectItem = new SwProjectItem();
        if (CollectionUtils.isEmpty(items)) {
            SwProjectItemExample example1 = new SwProjectItemExample();
            SwProjectItemExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andProjectItemEqualTo(params.getProjectItem());
            criteria1.andProjectIdEqualTo(NumberConstants.ONE);
            items = projectItemExtMapper.selectByExample(example1);

            newProjectItem.setProjectId(params.getProjectId());
            newProjectItem.setModuleType(items.get(0).getModuleType());
            newProjectItem.setUpdateBy(items.get(0).getUpdateBy());
            newProjectItem.setDetailType(items.get(0).getDetailType());
            newProjectItem.setProjectItem(params.getProjectItem());
            newProjectItem.setProjectValue(params.getProjectValue());
            newProjectItem.setOperator(RequestContext.getEmployeeNo());
        } else {
            newProjectItem.setId(items.get(0).getId());
            newProjectItem.setProjectValue(params.getProjectValue());
        }

        return projectItemExtMapper.insertOrUpdate(newProjectItem);
    }
}
