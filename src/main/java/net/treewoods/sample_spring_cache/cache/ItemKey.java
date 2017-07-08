/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.sample_spring_cache.cache;

import java.util.Objects;

/**
 *
 * @author toru
 */
public class ItemKey {
    
    
    private final String foo;
    private final String bar;

    public ItemKey(String foo, String bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public String getFoo() {
        return foo;
    }

    public String getBar() {
        return bar;
    }

    @Override
    public String toString() {
        return "ItemKey{" + "foo=" + foo + ", bar=" + bar + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.foo);
        hash = 17 * hash + Objects.hashCode(this.bar);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemKey other = (ItemKey) obj;
        if (!Objects.equals(this.foo, other.foo)) {
            return false;
        }
        if (!Objects.equals(this.bar, other.bar)) {
            return false;
        }
        return true;
    }
    
    
    
}
