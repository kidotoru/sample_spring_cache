package net.treewoods.sample_spring_cache.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheEventLog implements CacheEventListener{
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onEvent(CacheEvent ce) {
        log.info("Cache {}:key={},val={}",ce.getType(),ce.getKey(),ce.getNewValue());
    }    
}
