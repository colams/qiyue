package com.foxconn.sw.business.converter;

import com.foxconn.sw.data.dto.entity.CategoryDTO;
import com.foxconn.sw.data.entity.SwProperty;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToolCategoryConverter {

    public static List<CategoryDTO> toDto(List<SwProperty> categoryList) {
        if (CollectionUtils.isEmpty(categoryList)) {
            return null;
        }
        List<CategoryDTO> list = new ArrayList<>();
        categoryList.forEach(e -> {
            CategoryDTO categoryDTO = toDto(e);
            if (Objects.nonNull(categoryDTO)) {
                list.add(categoryDTO);
            }
        });
        return list;
    }

    public static CategoryDTO toDto(SwProperty property) {
        if (Objects.isNull(property)) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(property.getId());
        categoryDTO.setCategory(property.getCategory());
        categoryDTO.setPropertyName(property.getPropertyName());
        categoryDTO.setIcon(property.getIcon());
        return categoryDTO;
    }
}
