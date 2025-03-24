package com.foxconn.sw.data.dto.request.project.doc;

import com.foxconn.sw.data.dto.entity.MemberVo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AddFileParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "階段ID")
    @NotNull(message = "階段ID不能為空")
    private Integer phaseId;

    // ----------------------------------------
    @Schema(description = "所屬專案")
    private String project;

    @Schema(description = "階段")
    @NotBlank(message = "階段不能為空")
    private String phase;

    @Schema(description = "文件名稱")
    @NotBlank(message = "文件名稱不能為空")
    private String fileName;

    @Schema(description = "版本號")
    private String fileVersion;

    @Schema(description = "文件ID")
    @NotNull(message = "文件ID不能為空")
    @Min(value = 1, message = "文件ID不能為0")
    private Integer resourceID;

    @Schema(description = "文檔類型")
    @NotBlank(message = "文檔類型不能為空")
    private String category;

    @Schema(description = "文件類型")
    @NotBlank(message = "文件類型不能為空")
    private String mainType;

    @Schema(description = "次級類型")
    @NotBlank(message = "次級類型不能為空")
    private String subType;

    private String mainPart;

    @Schema(description = "供应商")
    private String supplier;

    @Schema(description = "文件來源")
    @NotBlank(message = "文件來源不能為空")
    private String source;

    @Schema(description = "作者")
    @NotBlank(message = "作者不能為空")
    private String author;

    private String config;
    private String issueMode;
    private String process;
    private String stage;
    private String extra;
    private String workType;
    private String deadLine;
    private String creator;


    @Schema(description = "保存期限")
    @NotBlank(message = "保存期限不能為空")
    private String expireDate;

    @Schema(description = "機密等級")
    @NotNull(message = "機密等級不能為空")
    private Integer secretLevel;

    private List<String> departmentIDs;
    private List<String> employeeNos;
    private List<MemberVo> views;

    @Schema(description = "下載權限")
    @NotNull(message = "下載權限不能為空")
    private Integer disableDown;

}
