package cr.ac.ucr.ecci.ci1310.cache.algorithm;
import cr.ac.ucr.ecci.ci1310.util.MyLinkedList.*;
import cr.ac.ucr.ecci.ci1310.cache.CacheMemory;

public class LastInFirstOut<K,V> extends CacheMemory<K,V> {

    public LastInFirstOut() {
        super();
    }

    public LastInFirstOut(int size) {
        super(size);
    }

    protected LinkedNode<CacheEntry> Lookup(K key) {
        LinkedNode<CacheEntry> node = this.elementTable.get(key);
        return node;
    }

    protected LinkedNode<CacheEntry> Insert(K key, V value) {

        // Ver si el caché está lleno
        if (this.numElem == this.size) {
           this.SelectVictim();
        }

        CacheEntry entry = new CacheEntry(key, value);
        this.elementList.addFirst(entry);
        this.elementTable.put(key, this.elementList.getFirst());
        this.numElem++;

        return this.elementList.getFirst();
    }

    protected LinkedNode<CacheEntry> Set(LinkedNode<CacheEntry> n, V value) {

        n.getElement().value = value;
        return n;
    }


    protected LinkedNode<CacheEntry> SelectVictim()
    {
        LinkedNode<CacheEntry> deleted = elementList.RemoveFirst();
        K key = deleted.getElement().key;
        this.elementTable.remove(key);
        this.numElem--;
        return deleted;
    }

    protected void Delete(LinkedNode<CacheEntry> node) {
        this.elementTable.remove(node.getElement().key);
        this.elementList.Remove(node);
        this.numElem--;
    }


    public void clear() {


    }

    protected int NumElem() {
        return this.numElem;
    }

}
