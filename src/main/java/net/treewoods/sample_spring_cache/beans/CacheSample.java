/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.sample_spring_cache.beans;

import net.treewoods.sample_spring_cache.cache.Item;
import net.treewoods.sample_spring_cache.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;


/**
 *
 * @author toru
 */
public class CacheSample implements Job{
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ItemService itemService;
    

    @Override
    public void execute(String[] args) {
        log.info("start.");
        
        StopWatch stopWatch = new StopWatch();
        
        Item item;

        item = new  Item("test-id");
        item.setName("A");
        itemService.put(item);

        log.info("最初の取得");
        stopWatch.start();
        item = itemService.find("test-id");
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());
        
        log.info("２回めの取得（キャッシュから）");
        stopWatch.start();
        item = itemService.find("test-id");
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());
        
        log.info("値の更新（キャッシュのクリア）");
        item = new  Item("test-id");
        item.setName("B");
        itemService.put(item);
        
        
        log.info("更新後の取得");
        stopWatch.start();
        itemService.find("test-id");
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());


        log.info("更新後の２回めの取得（キャッシュから）");
        stopWatch.start();
        itemService.find("test-id");
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());


        
        log.info("キャッシュ全クリア");
        itemService.clear();
        

        log.info("キャッシュ全クリア後の取得");
        stopWatch.start();
        itemService.find("test-id");
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());
        
        log.info("キャッシュ全クリア後の２回目の取得（キャッシュから）");
        stopWatch.start();
        itemService.find("test-id");
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());

        log.info("finish.");
    }
    
}
