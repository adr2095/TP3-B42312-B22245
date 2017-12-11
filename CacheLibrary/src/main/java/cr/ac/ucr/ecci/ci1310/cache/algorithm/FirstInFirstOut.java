package cr.ac.ucr.ecci.ci1310.cache.algorithm;
import cr.ac.ucr.ecci.ci1310.util.MyLinkedList.*;
import cr.ac.ucr.ecci.ci1310.cache.CacheMemory;

public class FirstInFirstOut <K,V> extends CacheMemory <K,V> {
    protected LinkedNode<CacheEntry> Lookup(K key) {
        return null;
    }

    protected LinkedNode<CacheEntry> Insert(K key, V value) {
        return null;
    }

    protected LinkedNode<CacheEntry> Set(LinkedNode<CacheEntry> n, V value) {
        return null;
    }


    protected LinkedNode<CacheEntry> SelectVictim()
    {
        return elementList.RemoveFirst();
    }

    protected void Delete(LinkedNode<CacheEntry> node) {

    }

    public void clear() {

    }
}
