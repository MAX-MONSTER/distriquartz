package com.wzhhan.distriquartz.executeclasss;

import com.alibaba.fastjson.JSON;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:monsterHan
 * @date:2019/9/16-10:39
 * @description:@TODO
 */
public class SysoutJob implements Job {
    private static Logger LOG = LoggerFactory.getLogger(SysoutJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       LOG.info("job_"+JSON.toJSONString(jobExecutionContext.getTrigger().getJobDataMap()));
    }
}
