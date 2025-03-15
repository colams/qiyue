package com.foxconn.sw.data.dto.request.project.settings;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 命令：重命名目錄
 */
@Data
public class RenameDirParams {

    @NotNull(message = "目錄ID不能為空")
    private Integer id;

    @NotNull(message = "新的目錄名")
    private String fileName;
}
