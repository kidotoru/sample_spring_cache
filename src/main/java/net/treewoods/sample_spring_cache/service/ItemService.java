package net.treewoods.sample_spring_cache.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.treewoods.sample_spring_cache.cache.Item;
import net.treewoods.sample_spring_cache.cache.ItemKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext applicationContext;

    private final Map<ItemKey, Item> storage = new HashMap<>();

    @Cacheable(cacheNames = "itemCache", key = "{#id.foo, #id.bar}")
    public Item find(ItemKey id) {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
        }
        return storage.getOrDefault(id, null);
    }

    @CachePut(cacheNames = "itemCache", key = "{#item.id.foo, #item.id.bar}")
    public Item update(Item item) {
        storage.put(item.getId(), item);
        return item;
    }

    @CacheEvict(cacheNames = "itemCache", key = "{#item.id, #item.id.bar}")
    public void put(Item item) {
        storage.put(item.getId(), item);
    }

    @CacheEvict(cacheNames = "itemCache", allEntries = true)
    public void clear() {
        log.info(applicationContext.getBean("CacheSample").toString());
    }
}
