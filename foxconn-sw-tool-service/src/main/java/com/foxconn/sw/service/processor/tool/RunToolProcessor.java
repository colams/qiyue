package com.foxconn.sw.service.processor.tool;

import com.foxconn.sw.business.tools.ToolsBusiness;
import com.foxconn.sw.common.utils.DomainRetrieval;
import com.foxconn.sw.common.utils.ExecToolUtils;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.data.constants.enums.FileAttrTypeEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.tool.RunToolParams;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class RunToolProcessor {
    @Autowired
    ToolsBusiness toolsBusiness;
    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    ExecToolUtils execToolUtils;

    /**
     * 运行工具
     *
     * @param toolParams
     * @param head
     * @return
     * @throws FileNotFoundException
     */
    public String runTool(RunToolParams toolParams, Header head) throws IOException, ScriptException {
        String employeeName = commonUserUtils.getEmployeeName(head.getToken());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String filePath = filePathUtils.getFilePath(FileAttrTypeEnums.TOOL.getSymbol()) + toolParams.getToolPath();
        String result = execToolUtils.execTool(filePath, toolParams.getToolParams());
        stopWatch.stop();
        String remark = String.format("%s 运行了软件", employeeName);
        toolsBusiness.saveRunResult(toolParams.getToolPath(), result, employeeName, remark, stopWatch.getTotalTimeMillis());
        return DomainRetrieval.getDomain() + "/result/" + result;
    }

}
