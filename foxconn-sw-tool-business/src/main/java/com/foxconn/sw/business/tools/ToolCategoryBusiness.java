package com.foxconn.sw.business.tools;

import com.foxconn.sw.business.converter.ToolCategoryConverter;
import com.foxconn.sw.data.entity.SwProperty;
import com.foxconn.sw.data.entity.SwPropertyExample;
import com.foxconn.sw.data.mapper.extension.tool.SwToolCategoryExtensionMapper;
import com.foxconn.sw.data.dto.entity.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolCategoryBusiness {

    @Autowired
    SwToolCategoryExtensionMapper swToolCategoryExtensionMapper;

    public List<CategoryDTO> listAll() {
        List<SwProperty> categoryList = swToolCategoryExtensionMapper.selectByExample(null);
        List<CategoryDTO> categoryDTOList = ToolCategoryConverter.toDto(categoryList);
        return categoryDTOList;
    }

    public List<CategoryDTO> listByType(Integer category) {
        SwPropertyExample example = new SwPropertyExample();
        SwPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryEqualTo(category);
        List<SwProperty> categoryList = swToolCategoryExtensionMapper.selectByExample(example);
        List<CategoryDTO> categoryDTOList = ToolCategoryConverter.toDto(categoryList);
        return categoryDTOList;
    }

}
