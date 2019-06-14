package com.yjq.data.service.alarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;
/**
 * @author netyjq@gmail.com
 * @date 2018/4/9
 */
@Component
public class MailSendHandler implements InitializingBean {

    public static final Logger logger = LoggerFactory.getLogger(MailSendHandler.class);

    /**
     * 发送告警的邮箱账号
     */
    @Value("${alarm.mail.address}")
    private String SENDER;

    /**
     * 发送告警的邮箱密码
     */
    @Value("${alarm.mail.password}")
    private String PASSWORD;

    /**
     * 邮箱服务器
     */
    @Value("${alarm.mail.server}")
    private String MAIL_SERVER_HOST;

    private JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     * @param receiver 收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    protected boolean send(String receiver, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setFrom(SENDER);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            mailSender.send(simpleMailMessage);
            logger.info(">>> 告警邮件发送成功. receiver: {}, content: {}", receiver, content);
        } catch (Exception e) {
            logger.error(">>> 告警邮件发送异常. receiver: {}, content: {}", receiver, content, e);
            return false;
        }
        return true;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(SENDER);
        mailSender.setPassword(PASSWORD);
        // 发件人邮箱的 SMTP 服务器地址
        mailSender.setHost(MAIL_SERVER_HOST);
        mailSender.setPort(465);
        //协议SMTP+SSL
        mailSender.setProtocol("smtps");
        mailSender.setDefaultEncoding("utf-8");
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.ssl.enable", true);
        mailSender.setJavaMailProperties(javaMailProperties);
    }
}