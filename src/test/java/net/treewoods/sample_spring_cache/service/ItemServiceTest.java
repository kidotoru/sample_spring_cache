/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.sample_spring_cache.service;

import java.util.concurrent.TimeUnit;
import net.treewoods.sample_spring_cache.cache.Item;
import net.treewoods.sample_spring_cache.cache.ItemKey;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

/**
 *
 * @author toru
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
public class ItemServiceTest {
    
    @Autowired
    private ItemService itemService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    
    public ItemServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class ItemService.
     */
    @Test
    public void testFind() {
        
String expected = "Hello World";
String actual = "Hello" + " " + "World";
assertThat(actual, is(expected));

        System.out.println("find");
        log.info("start.");
        
        StopWatch stopWatch = new StopWatch();
        
        Item item;

        item = new  Item(new ItemKey("A","test-id"));
        item.setName("A");
        itemService.put(item);

        item = new  Item(new ItemKey("B","test-id"));
        item.setName("B");
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

    /**
     * Test of update method, of class ItemService.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
    }

    /**
     * Test of put method, of class ItemService.
     */
    @Test
    public void testPut() {
        System.out.println("put");
    }

    /**
     * Test of clear method, of class ItemService.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
    }
    
}
