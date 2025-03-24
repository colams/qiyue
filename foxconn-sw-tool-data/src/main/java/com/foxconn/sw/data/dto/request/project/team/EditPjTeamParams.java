package com.foxconn.sw.data.dto.request.project.team;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EditPjTeamParams {

    @Schema(description = "ID")
    @NotNull(message = "ID不能为空")
    private Integer id;

    @Schema(description = "專案ID")
    @NotBlank(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "行政部門")
    @NotBlank(message = "行政部門不能為空")
    private String adminDivision;

    @Schema(description = "部門")
    @NotBlank(message = "部門不能為空")
    private String dept;

    @Schema(description = "通用職務劃分")
    @NotBlank(message = "通用職務不能為空")
    private String jobTitle;

    @Schema(description = "工號")
    @NotBlank(message = "工號不能為空")
    private String empNo;

    @Schema(description = "中文名")
    @NotBlank(message = "中文名不能為空")
    private String nameCn;

    @Schema(description = "英文名")
    @NotBlank(message = "英文名不能為空")
    private String nameEn;

    @Schema(description = "集團郵件地址")
    @NotBlank(message = "集團郵件地址不能為空")
    private String email;

    @Schema(description = "集團座機或其他電話")
    @NotBlank(message = "集團座機或其他電話不能為空")
    private String tel;

    @Schema(description = "製程")
    @NotBlank(message = "製程不能為空")
    private String processor;

    @Schema(description = "站位")
    @NotBlank(message = "站位不能為空")
    private String station;

    @Schema(description = "備註")
    private String remark;
}
