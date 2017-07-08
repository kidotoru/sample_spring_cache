package net.treewoods.sample_spring_cache.beans;

import java.util.concurrent.TimeUnit;
import net.treewoods.sample_spring_cache.cache.Item;
import net.treewoods.sample_spring_cache.cache.ItemKey;
import net.treewoods.sample_spring_cache.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

public class CacheSample implements Job {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ItemService itemService;
    
    @Override
    public void execute(String[] args) {
        log.info("start.");
        
        StopWatch stopWatch = new StopWatch();
        
        Item item;

        item = new Item(new ItemKey("A","test-id"));
        item.setName("A");
        itemService.put(item);

        log.info("最初の取得");
        stopWatch.start();
        item = itemService.find(new ItemKey("A","test-id"));
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());
        
        log.info("２回めの取得（キャッシュから）");
        stopWatch.start();
        item = itemService.find(new ItemKey("A","test-id"));
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());
        
        log.info("値の更新（キャッシュのクリア）");
        item = new  Item(new ItemKey("A","test-id"));
        item.setName("B");
        itemService.put(item);
        
        
        log.info("更新後の取得");
        stopWatch.start();
        item = itemService.find(new ItemKey("A","test-id"));
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());


        log.info("更新後の２回めの取得（キャッシュから）");
        stopWatch.start();
        item = itemService.find(new ItemKey("A","test-id"));
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());


        
        log.info("キャッシュ全クリア");
        itemService.clear();
        

        log.info("キャッシュ全クリア後の取得");
        stopWatch.start();
        item = itemService.find(new ItemKey("A","test-id"));
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());
        
        log.info("キャッシュ全クリア後の２回目の取得（キャッシュから）");
        stopWatch.start();
        item = itemService.find(new ItemKey("A","test-id"));
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());

        
        for(int i = 1; i <= 10; i++) {
            item = new Item(new ItemKey("A","key-" + String.valueOf(i)));
            item.setName("val-" + String.valueOf(i));
            itemService.put(item);
            itemService.find(new ItemKey("A","key-" + String.valueOf(i)));
        }
        
        
        log.info("キャッシュ全クリア後の２回目の取得（キャッシュからとれない）");
        stopWatch.start();
        item = itemService.find(new ItemKey("A","test-id"));
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());

        log.info("キャッシュ全クリア後の２回目の取得");
        stopWatch.start();
        item = itemService.find(new ItemKey("A","test-id"));
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());
        

        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
        }        

        log.info("TTL（キャッシュからとれない）");
        stopWatch.start();
        item = itemService.find(new ItemKey("A","test-id"));
        stopWatch.stop();
        log.info("value={} elapsed time={}",item.getName(), stopWatch.getLastTaskInfo().getTimeSeconds());

        
        log.info("finish.");
    }
    
}
