package net.treewoods.sample_spring_cache;

import net.treewoods.sample_spring_cache.beans.Job;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class NewMain {

    public static final String SPRING_CONF_PATH = "conf/applicationContext.xml";

    public static void main(String[] args) {

        ApplicationContext context
                = new FileSystemXmlApplicationContext(SPRING_CONF_PATH);
        Job job = (Job) context.getBean("CacheSample");
        job.execute(args);

    }

}
