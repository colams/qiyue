package com.foxconn.sw.service.controller.account;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.entity.acount.LoginParams;
import com.foxconn.sw.service.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

public class UserControllerTest extends BaseTest {

    @Autowired
    UserController userController;

    @Test
    public void register() {

    }

    @Test
    public void resetPwd() {
    }

    @Test
    public void login() {
        Request<LoginParams> request = initRequest(() -> initUser());
        // Response response = userController.login(request);
        // JsonUtils.serialize(response);
    }

    @Test
    public void list() {
    }

    @Test
    public void detail() {
    }

    private Request initRequest(Supplier supplier) {
        Request request = new Request();
        request.setData(supplier.get());
        Header header = new Header();
        header.setToken("test-token-123");
        request.setHead(header);
        request.setTimeStamp(DateTimeUtils.getTimeStamp());
        request.setTraceId(UUIDUtils.getUuid());
        return request;
    }

    private LoginParams initUser() {
        LoginParams params = new LoginParams();
        params.setUserName("colams");
        params.setPassword("123456");
        params.setAuthCode("");
        return params;
    }
}