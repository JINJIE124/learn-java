package com.example.springbootdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author JIN Jay CI/DAV4.8
 * @date 5/5/2019 9:44 AM
 */
@Slf4j
@Component
public class ScheduledTask {

    /**
     * Cron 表达式 cron = "* * * * * * *"
     * 对应关系 秒 分 小时 日期 月份 星期 年
     * 参考博客 https://www.cnblogs.com/javahr/p/8318728.html
     */



    @Scheduled(cron = "0/10 * * * * *")  //每10秒钟触发一次
    public void task1() {
        log.error("-------------this is task 1----------------");
    }

    @Scheduled(cron = "0 27 10 * * *")   //每天上午10：27触发一次
    public void task2() {
        log.error("-------------this is task 2----------------");
    }

    @Scheduled(cron = "0 30 10 5 * *")   //每月5号10:30触发一次
    public void task3() {
        log.error("-------------this is task 3----------------");
    }

    /**
     * fixedRate=6000 上一次开始执行时间点之后6秒
     * fixedDelay=6000 上一次执行完毕时间点之后6秒
     * initialDelay=1000, fixedRate=6000 第一次延迟一秒后执行
     */

    @Scheduled(fixedRate = 3000)
    public void task4() {
        log.error("-------------this is task 4----------------");
    }

}
