package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskAssignParams;
import com.foxconn.sw.service.BaseTest;
import com.foxconn.sw.service.processor.oa.task.AssignTaskProcessor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AssignTaskProcessorTest extends BaseTest {

    @Autowired
    AssignTaskProcessor taskProcessor;

    @Test
    public void assignTask() {
        TaskAssignParams params = initParams();
        Header head = initHead();
        boolean result = taskProcessor.assignTask(params, head);
        System.out.println(result);
    }

    private TaskAssignParams initParams() {
        TaskAssignParams taskAssignParams = new TaskAssignParams();
        taskAssignParams.setTaskId(1);
        taskAssignParams.setAssignEid("colams");
        taskAssignParams.setContent("任务分派");
        return taskAssignParams;
    }
}