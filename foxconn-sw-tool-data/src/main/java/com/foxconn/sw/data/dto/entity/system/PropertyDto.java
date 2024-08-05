package com.foxconn.sw.data.dto.entity.system;

import org.springframework.stereotype.Component;

@Component
public class PropertyDto {

    private int category;
    private String propertyName;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
