package net.treewoods.sample_spring_cache.cache;

import java.io.Serializable;

public class Item implements Serializable {
    
    private String id;
    private String name;

    public Item(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + '}';
    }
}
