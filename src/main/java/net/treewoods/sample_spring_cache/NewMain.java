/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.sample_spring_cache;

import net.treewoods.sample_spring_cache.beans.Job;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author toru
 */
public class NewMain {
    
    public static final String SPRING_CONF_PATH = "./conf/applicationContext.xml";
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            ApplicationContext context
                    = new FileSystemXmlApplicationContext(SPRING_CONF_PATH);
            Job job = (Job) context.getBean("CacheSample");
            job.execute(args);
        
        
    }
    
}
