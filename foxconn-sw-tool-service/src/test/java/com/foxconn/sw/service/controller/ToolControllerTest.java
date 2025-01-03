package com.foxconn.sw.service.controller;


import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.service.BaseTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class ToolControllerTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ToolControllerTest.class);


    @Test
    public void saveTool() {
        Response response = new Response();
        Request<SwToolDTO> request = new Request<>();
        MultipartFile multipartFile = new MockMultipartFile("fileName", new byte[]{});
        // Mockito.doReturn(response).when(toolController.saveTool(request, multipartFile));
        logger.info("test saveTool over");
        //Assert.isNull(Objects.isNull(response), "unit test error");
    }

    @Test
    public void testListHistoryTool() {
        Response response = new Response();
        Request<Integer> request = new Request<>();
        // Mockito.doReturn(response).when(toolController.listToolHistory(request));
        logger.info("test listToolHistory over");
        // Assert.isNull(Objects.isNull(response), "unit test error");
    }
}