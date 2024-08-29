package com.foxconn.sw.business.tools;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.CategoryDTO;
import com.foxconn.sw.service.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ToolCategoryBusinessTest extends BaseTest {

    @Autowired
    ToolCategoryBusiness toolCategoryBusiness;


    @Test
    public void listAll() {
        List<CategoryDTO> categoryDTOList = toolCategoryBusiness.listAll();
        System.out.println(JsonUtils.serialize(categoryDTOList));
    }
}