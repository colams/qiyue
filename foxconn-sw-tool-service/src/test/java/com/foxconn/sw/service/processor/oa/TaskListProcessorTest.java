package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.task.TaskParams;
import com.foxconn.sw.service.BaseTest;
import com.foxconn.sw.service.processor.task.TaskListProcessor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskListProcessorTest extends BaseTest {

    @Autowired
    TaskListProcessor taskListProcessor;

    @Test
    public void list() {
        PageParams<TaskParams> taskParams = initParam();
        var result = taskListProcessor.list(taskParams);
        logger.info(JsonUtils.serialize(result));

    }

    private PageParams<TaskParams> initParam() {
        TaskParams taskParams = new TaskParams();
        taskParams.setKeyWord("任务管理");
        PageParams pageParams = new PageParams();
        pageParams.setParams(taskParams);
        pageParams.setCurrentPage(1);
        pageParams.setPageSize(10);
        return pageParams;
    }
}