package com.lugew.springbootddd;

/**
 * @author 夏露桂
 * @since 2021/6/8 17:40
 */
public abstract class ValueObject<T> {
    @Override
    public boolean equals(Object obj) {
        T valueObject = (T) obj;
        if (valueObject == null)
            return false;
        return equalsCore(valueObject);
    }

    @Override
    public int hashCode() {
        return getHashCodeCore();
    }

    protected abstract boolean equalsCore(T other);

    protected abstract int getHashCodeCore();
}
