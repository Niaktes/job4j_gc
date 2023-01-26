package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            V value = load(key);
            put(key, value);
            return value;
        }
        V value = cache.get(key).get();
        if (value != null) {
            return value;
        } else {
            throw new NoSuchElementException("There are no text for this file.");
        }
    }

    protected abstract V load(K key);

}