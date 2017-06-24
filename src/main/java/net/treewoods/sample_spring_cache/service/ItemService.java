/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.sample_spring_cache.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.treewoods.sample_spring_cache.cache.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author toru
 */
@Service
public class ItemService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
     private Map<String, Item> storage = new HashMap<>();

    @Cacheable(cacheNames = "itemCache", key = "#id")
    public Item find(String id) {
        Item item = null;
        try {
            TimeUnit.SECONDS.sleep(3L);
            item = storage.getOrDefault(id, null);
        } catch (InterruptedException e) {
        }

        return item;
    }

    @CachePut(cacheNames = "itemCache", key = "#item.id")
    public Item update(Item item) {
        
        storage.put(item.getId(), item);
        
        return item;
    }

    @CacheEvict(cacheNames = "itemCache", key = "#item.id")
    public void put(Item item) {
        storage.put(item.getId(), item);
    }
    
    @CacheEvict(cacheNames = "itemCache", allEntries = true)
    public void clear(){
        
    }

}
