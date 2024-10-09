package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefDetailVo;
import com.foxconn.sw.service.BaseTest;
import com.foxconn.sw.service.processor.oa.task.CreateTaskProcessor;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateTaskProcessorTest extends BaseTest {

    @Autowired
    CreateTaskProcessor createTaskProcessor;


    @Test
    public void createTask() {
        TaskBriefDetailVo data = initVo();
        Header head = initHead();
        var result = createTaskProcessor.createTask(data);
        System.out.println("createTask=================:" + result);
    }


    public static TaskBriefDetailVo initVo() {
        TaskBriefDetailVo vo = new TaskBriefDetailVo();
        vo.setProject("系统開發");
        vo.setCategory("软件BUG");
        vo.setLevel("特急");
        vo.setDeadLine("2024-08-20");
        vo.setTitle("任务管理工具在Chrome浏览器打不开");
        vo.setDescription("网页能够兼容市面上的大多数浏览器，网页能够兼容更多的操作系统，网页能够自适应不同显示界面的尺寸");
        vo.setResourceIds(Lists.newArrayList(1, 2, 3, 4));
        vo.setManagerEid("05147");
        return vo;
    }

}