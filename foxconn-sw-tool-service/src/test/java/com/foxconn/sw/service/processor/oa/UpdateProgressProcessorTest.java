package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.task.TaskProgressBriefParams;
import com.foxconn.sw.service.BaseTest;
import com.foxconn.sw.service.processor.task.UpdateProgressProcessor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateProgressProcessorTest extends BaseTest {

    @Autowired
    UpdateProgressProcessor updateProgressProcessor;

    @Test
    public void updateProgress() {
        Header head = initHead();
        TaskProgressBriefParams briefParams = initParams();
        boolean result = updateProgressProcessor.updateProgress(briefParams);
        System.out.println(result);
    }

    private TaskProgressBriefParams initParams() {
        TaskProgressBriefParams params = new TaskProgressBriefParams();
        params.setTaskId(1);
        params.setContent("更新任务进度");
        params.setProgress(20);
        return params;
    }
}