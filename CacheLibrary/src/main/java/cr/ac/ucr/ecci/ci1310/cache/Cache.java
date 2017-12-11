package cr.ac.ucr.ecci.ci1310.cache;

public interface Cache<K, V> {
    String getName();
    V get(K var1);
    void put(K var1, V var2);
    void evict(K var1);
    void clear();
}

