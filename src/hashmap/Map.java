package hashmap;

import java.util.Iterator;

public interface Map<K,V> {
    int size();

    boolean isEmpty();

    void clear();

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    Iterator keys();
    Iterator values();

}
