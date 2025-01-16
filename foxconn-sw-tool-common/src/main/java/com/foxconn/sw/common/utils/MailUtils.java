package com.foxconn.sw.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * 公司邮件发送类
 * rayplus-oa@mail.foxconn.com
 * Password.123
 * 服務:
 * 伺服器IP地址 主10.134.28.97：25
 * SMTP伺服器 備10.134.99.130:25
 */
public class MailUtils {
    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    private static final String HOST = "10.134.28.97";//邮件服务器的SMTP地址，可选，默认为smtp.<发件人邮箱后缀>
    private static final int PORT = 25;// 邮件服务器的SMTP端口，可选，默认25,110,485
    private static final String FROM = "rayplus-oa@mail.foxconn.com";//发件人（必须正确，否则发送失败）
    private static final String USER = "rayplus-oa";//用户名，默认为发件人邮箱前缀
    private static final String PASS = "Password.123";//密码（注意，某些邮箱需要为SMTP服务单独设置授权码)


    public static MailAccount getMailCount() {
        MailAccount account = new MailAccount();
        account.setHost(HOST);
        account.setPort(PORT);
        account.setAuth(true);
        account.setFrom(FROM);
        account.setUser(USER);
        account.setPass(PASS);
        return account;
    }

    /**
     * 发送含有附件的邮件
     */
    public static void sendEnclosureEmail() {
        MailAccount account = getMailCount();
        ArrayList<String> mailList = CollUtil.newArrayList("zhan-teng.zhang@mail.foxconn.com");
        String result = "";

        try {
            result = MailUtil.send(account, mailList, " Mail Test", "<b>测试二维码</b><br/>", false);
        } catch (Exception e) {
            result = e.toString();
        } finally {
            logger.info(result);
        }
    }


    public static void main(String[] args) {
        sendEnclosureEmail();
    }
}
