package hashmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashMap<K, V> implements Map<K,V> {
    private int capacity;
    private double loadFactor;
    private Entry<K, V>[] table;
    private int size = 0;
    private int placeholder = 0;

    public HashMap(int capacity, double loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        if (loadFactor <= 0 || loadFactor >= 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
    }

    public HashMap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.loadFactor = 0.75;
        this.table = new Entry[capacity];
    }

    public HashMap() {
        this.capacity = 11;
        this.loadFactor = 0.75;
        this.table = new Entry[11];
    }

    public Entry<K, V>[] getTable() {
        return table;
    }

    public int size() {
        return size;
    }

    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        }
        int bucket = getMatchingOrNextAvailableBucket(key);

        V returnValue = null;
        if (table[bucket] != null) {
            returnValue = table[bucket].getValue();
            if (table[bucket].getValue() == null) {
                size++;
            }
        }
        else {
            size++;

            int threshold = (int)(capacity * loadFactor);
            if (size + placeholder == threshold) {
                resize();
                rehash(capacity);
            }

        }
        bucket = getMatchingOrNextAvailableBucket(key);
        table[bucket] = new Entry<K, V>(key, value);

        return returnValue;
    }

    private void resize() {
        capacity = capacity * 2 + 1;
        while (!checkPrime(capacity)) {
            capacity++;
        }
    }
    private void rehash(int capacity) {
        Entry<K, V>[] oldTable = table;
        table = new Entry[capacity];
        for (Entry<K, V> entry:
                oldTable) {
            if (entry != null) {
                int bucket = getMatchingOrNextAvailableBucket(entry.getKey());
                table[bucket] = new Entry<K, V>(entry.getKey(), entry.getValue());
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        table = new Entry[capacity];
        size = 0;
        placeholder = 0;
    }

    public int getMatchingOrNextAvailableBucket(K key) {
        int bucket = key.hashCode() % table.length;
        while (table[bucket] != null && !table[bucket].getKey().equals(key)) {
            bucket++;
            if (bucket >= table.length) {
                bucket = 0;
            }
        }
        return bucket;
    }

    public V get(K key) {
        V returnValue = null;
        int bucket = getMatchingOrNextAvailableBucket(key);
        if (table[bucket] != null) {
            returnValue = table[bucket].getValue();
        }
        return returnValue;
    }

    public V remove(K key) {
        V returnValue = get(key);
        int bucket = getMatchingOrNextAvailableBucket(key);
        if (table[bucket] != null) {
            table[bucket] = new Entry<>(key, null);
        }
        size--;
        placeholder++;
        // remove function will not decrease the size including key placeholder.
        return returnValue;
    }

    public Iterator<V> values() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> entry:
                table) {
            if (entry != null){
                if (entry.getValue() != null) {
                    values.add(entry.getValue());
                }
            }
        }
        return values.iterator();
    }

    public Iterator<K> keys() {
        List<K> keys = new ArrayList<>();
        for (Entry<K, V> entry:
                table) {
            if (entry != null){
                if (entry.getValue() != null) {
                    keys.add(entry.getKey());
                }
            }
        }
        return keys.iterator();
    }

    private boolean checkPrime(int num) {
        boolean isPrime = true;
        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
