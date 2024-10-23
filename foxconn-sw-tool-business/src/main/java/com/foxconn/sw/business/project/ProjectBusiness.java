package com.foxconn.sw.business.project;

import com.foxconn.sw.data.entity.SwProjectList;
import com.foxconn.sw.data.entity.SwProjectListExample;
import com.foxconn.sw.data.mapper.extension.project.SwProjectListExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectBusiness {

    @Autowired
    SwProjectListExtMapper projectListExtMapper;

    public List<SwProjectList> queryProjectList() {
        SwProjectListExample example = new SwProjectListExample();
        SwProjectListExample.Criteria criteria = example.createCriteria();

        return projectListExtMapper.selectByExample(example);
    }

}
