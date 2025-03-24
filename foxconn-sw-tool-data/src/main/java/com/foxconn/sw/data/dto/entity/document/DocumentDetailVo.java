package com.foxconn.sw.data.dto.entity.document;

import com.foxconn.sw.data.dto.entity.MemberVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(description = "文件信息")
@Data
@Accessors(chain = true)
public class DocumentDetailVo {

    @Schema(description = "文件or目錄ID")
    private Integer id;

    @Schema(description = "文件or目錄名")
    private String name;

    @Schema(description = "文件分類")
    private String category;

    @Schema(description = "下載URL")
    private String downloadUrl;

    @Schema(description = "資源ID")
    private Integer resourceId;

    @Schema(description = "文件大小，單位KB")
    private Long size;

    @Schema(description = "預覽URL")
    private String viewUrl;

    @Schema(description = "專案ID")
    private Integer projectId;

    @Schema(description = "專案名稱")
    private String project;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "部門名稱")
    private String department;

    @Schema(description = "版本號")
    private String fileVersion;

    @Schema(description = "0-公共文檔，1-私人文檔")
    private String fileType;

    @Schema(description = "是否可預覽")
    private Boolean canView;

    @Schema(description = "1-不可下载")
    private Integer disableDown;

    @Schema(description = "是否可修改")
    private Boolean canUpdate;

    @Schema(description = "作者")
    private EmployeeVo author;

    @Schema(description = "上傳者")
    private EmployeeVo publisher;

    @Schema(description = "上傳時間")
    private String createTime;

    @Schema(description = "最後修改時間")
    private String updateTime;

    @Schema(description = "機密等級")
    private Integer level;

    @Schema(description = "機密等級：中文描述")
    private String title;

    @Schema(description = "")
    private String workType;

    @Schema(description = "")
    private String mainType;

    @Schema(description = "")
    private String subType;

    @Schema(description = "")
    private String mainPart;

    @Schema(description = "文件來源")
    private String source;

    @Schema(description = "配置")
    private String config;

    @Schema(description = "供應商")
    private String supplier;

    @Schema(description = "製程")
    private String process;

    @Schema(description = "")
    private String stage;

    @Schema(description = "")
    private String issueMode;

    @Schema(description = "")
    private String deadLine;

    @Schema(description = "階段ID")
    private Integer phaseId;

    @Schema(description = "階段名")
    private String phase;

    @Schema(description = "過期時間")
    private String expireDate;

    @Schema(description = "1-已歸檔")
    private Integer archived;

    @Schema(description = "")
    private List<String> departmentIDs;

    @Schema(description = "")
    private List<String> employeeNos;

    @Schema(description = "")
    private String extra;

    @Schema(description = "")
    private List<MemberVo> views;
}
