package net.treewoods.sample_spring_cache.cache;

import java.io.Serializable;

public class Item implements Serializable {
    
    private ItemKey id;
    private String name;

    public Item(ItemKey id) {
        this.id = id;
    }

    public ItemKey getId() {
        return id;
    }

    public void setId(ItemKey id) {
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
        return "Item{" + "id=" + id.toString() + ", name=" + name + '}';
    }
}
