package com.foxconn.sw.data.dto.entity.oa;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class MonthlyWorkParams {

    @NotNull
    @Length(max = 4,min = 4,message = "請輸入正確的年份")
    private String year;

    private int types;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }
}
