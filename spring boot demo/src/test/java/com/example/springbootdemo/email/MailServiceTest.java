package com.example.springbootdemo.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.junit.Assert.*;

/**
 * @author JIN Jay CI/DAV4.8
 * @date 5/5/2019 2:18 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("1240887078@qq.com","test simple mail","hello this is simple mail");
    }


    @Test
    public void sendHtmlMail() {
        String content = "<html>\n" +
                            "<body>\n" +
                                "<h3>Hello World!这是一封HTML邮件！</h3>\n" +
                            "</body>\n" +
                         "</html>";
        mailService.sendHtmlMail("fixed-term.Jay.JIN@cn.bosch.com","test html mail",content);
    }

    @Test
    public void sendTemplateMail() {
        Context context = new Context();
        context.setVariable("name","JIN JAY");
        String emailContent = templateEngine.process("EmailTemplate",context);
        mailService.sendHtmlMail("fixed-term.Jay.JIN@cn.bosch.com","test html mail",emailContent);
    }


    @Test
    public void sendAttachmentsMail() {
        String filePath = "C:\\Users\\JAJ5SZH\\Desktop\\DailyWorksheet_Jay.xlsx";
        mailService.sendAttachmentsMail("1240887078@qq.com","test attachment email","有附件，请注意查收！",filePath);
    }
}