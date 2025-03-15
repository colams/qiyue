package com.foxconn.sw.data.dto.request.state;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StateParams {
    private Integer id;

    private String nodeName;

    @Schema(hidden = true)
    private Integer priority;

    @Schema(description = "是否可刪除標記：0-不可刪除，1-可刪除")
    @NotNull(message = "是否可刪除標記不能為空")
    private Integer canDel;
}
