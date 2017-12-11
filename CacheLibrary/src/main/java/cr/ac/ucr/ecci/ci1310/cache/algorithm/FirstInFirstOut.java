package cr.ac.ucr.ecci.ci1310.cache.algorithm;
import cr.ac.ucr.ecci.ci1310.util.MyLinkedList.*;
import cr.ac.ucr.ecci.ci1310.cache.CacheMemory;

public class FirstInFirstOut <K,V> extends CacheMemory <K,V> {
    public FirstInFirstOut(int size) {
        super(size);
    }

    public FirstInFirstOut() {
        super();
    }

    /*protected LinkedNode<CacheEntry> Lookup(K key) {
        LinkedNode<CacheEntry> node = this.elementTable.get(key);
        return node;
    }*/

    /*protected LinkedNode<CacheEntry> Insert(K key, V value) {
        return null;
    }*/

    /*protected LinkedNode<CacheEntry Set(LinkedNode<CacheEntry> n, V value) {
        return null;
    }*/

    /*protected LinkedNode<CacheEntry> SelectVictim()
    {
        LinkedNode<CacheEntry> deleted = elementList.RemoveLast();
        K key = deleted.getElement().key;
        this.elementTable.remove(key);
        this.numElem--;
        return deleted;
    }*/

    /*protected void Delete(LinkedNode<CacheEntry> node) {

    }*/

    public void clear() {

    }
}
