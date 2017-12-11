package cr.ac.ucr.ecci.ci1310.cache.algorithm;

import cr.ac.ucr.ecci.ci1310.cache.CacheMemory;
import cr.ac.ucr.ecci.ci1310.util.MyLinkedList.LinkedNode;
import org.codehaus.groovy.runtime.metaclass.MetaMethodIndex;
import sun.awt.image.ImageWatched;

import java.util.Set;

public class LeastRecentlyUsed<K,V> extends CacheMemory<K,V> {
    public LeastRecentlyUsed() {
        super();
    }

    public LeastRecentlyUsed(int size) {
        super(size);
    }

    public LinkedNode<CacheEntry> Lookup(K key) {
        LinkedNode<CacheEntry> node = this.elementTable.get(key);

        if(node != null) {
            node = updateElementList(node);
            this.elementTable.remove(key);
            this.elementTable.put(key, node);
        }
        return node;
    }

    private LinkedNode<CacheEntry> updateElementList(LinkedNode<CacheEntry> node) {
        if (elementList.NumElem() > 1 && node != this.elementList.getFirst()) {
            this.elementList.Remove(node);
            this.elementList.addFirst(node.getElement());
            node = this.elementList.getFirst();
        }
        return node;
    }

    /*protected LinkedNode<CacheEntry> Insert(K key, V value) {

        // Ver si el caché está lleno
        if (this.numElem == this.size) {
           this.SelectVictim();
        }

        CacheEntry entry = new CacheEntry(key, value);
        this.elementList.addFirst(entry);
        this.elementTable.put(key, this.elementList.getFirst());
        this.numElem++;

        return this.elementList.getFirst();
    }*/

    /*protected LinkedNode<CacheEntry> Set(LinkedNode<CacheEntry> n, V value) {

        n.getElement().value = value;
        return n;
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
        this.elementTable.remove(node.getElement().key);
        this.elementList.Remove(node);
        this.numElem--;
    }*/


    public void clear() {


    }

    protected int NumElem() {
        return this.numElem;
    }
}
