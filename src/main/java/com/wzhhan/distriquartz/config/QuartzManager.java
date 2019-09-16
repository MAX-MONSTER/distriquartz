package com.wzhhan.distriquartz.config;

import com.wzhhan.distriquartz.bean.JobConfig;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/**
 * @author:monsterHan
 * @date:2019/9/16-10:47
 * @description:@TODO
 */

@Configuration
public class QuartzManager {
    @Autowired
    private Scheduler scheduler;

    /**
     * 新建一个任务
     */
    public String addJob(JobConfig appQuartz) throws Exception {


        if (!CronExpression.isValidExpression(appQuartz.corn)) {
            return "Illegal cron expression";   //表达式格式不正确
        }
        JobDetail jobDetail = null;
        //构建job信息
        jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(appQuartz.clazz)).withIdentity(appQuartz.name, appQuartz.group).build();


        //表达式调度构建器(即任务执行的时间,不立即执行)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(appQuartz.corn).withMisfireHandlingInstructionDoNothing();

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(appQuartz.name, appQuartz.group).usingJobData(new JobDataMap(appQuartz.dataMap))
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        return "success";
    }
}
