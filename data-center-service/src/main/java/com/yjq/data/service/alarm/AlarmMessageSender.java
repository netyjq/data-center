package com.yjq.data.service.alarm;

/**
 * 邮件发送器
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
public interface AlarmMessageSender {

    /**
     * 发送邮件
     * @param alarmChecker 告警检查器
     */
    void send(AlarmChecker alarmChecker);

}
