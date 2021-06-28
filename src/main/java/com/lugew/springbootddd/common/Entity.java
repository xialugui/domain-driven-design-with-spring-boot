package com.lugew.springbootddd.common;

/**
 * @author 夏露桂
 * @since 2021/6/8 17:25
 */
public abstract class Entity {

    protected long id;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Entity)) {
            return false;
        }
        Entity other = (Entity) obj;
        if (this == other) // Reference equality
            return true;
        if (!this.getClass().equals(other.getClass()))
            return false;
        if (this.id == 0 || other.getId() == 0)
            return false;
        return this.id == other.getId(); //identifier equality
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

