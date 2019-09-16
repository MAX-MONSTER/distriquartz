package com.wzhhan.distriquartz;

import com.wzhhan.distriquartz.bean.JobConfig;
import com.wzhhan.distriquartz.config.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author:monsterHan
 * @date:2019/9/16-11:02
 * @description:@TODO
 */
@Component
public class RunApp implements ApplicationRunner {
    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobConfig jf = new JobConfig();
        jf.clazz = "com.wzhhan.distriquartz.executeclasss.SysoutJob";
        jf.corn = "0/5 * * * * ?";
        jf.name = UUID.randomUUID().toString();
        jf.dataMap.put("data",jf.name );
        jf.group = "hh";
        quartzManager.addJob(jf);
    }
}
