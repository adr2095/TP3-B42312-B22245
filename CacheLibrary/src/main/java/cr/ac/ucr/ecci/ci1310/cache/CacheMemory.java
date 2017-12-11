package cr.ac.ucr.ecci.ci1310.cache;
import cr.ac.ucr.ecci.ci1310.util.MyLinkedList.LinkedNode;
import cr.ac.ucr.ecci.ci1310.util.MyLinkedList.LinkedList;
import org.codehaus.groovy.runtime.metaclass.MetaMethodIndex;

import java.util.HashMap;

public abstract class CacheMemory <K,V> implements Cache <K,V>{

    public CacheMemory() {
        this.size = 10;
        this.numElem = 0;
        int mapCapacity = (int) Math.ceil((10+1)/0.75);
        this.elementTable = new HashMap<K,LinkedNode<CacheEntry>>(mapCapacity);
        this.elementList = new LinkedList<CacheEntry>();
    }

    public CacheMemory(int size, String name) {
        this.size = size;
        this.numElem = 0;
        int mapCapacity = (int) Math.ceil((size+1)/0.75);
        this.elementTable = new HashMap<K,LinkedNode<CacheEntry>>(mapCapacity);
        this.elementList = new LinkedList<CacheEntry>();
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    /**
     * Esta clase encapsula los datos que se guardan en el caché.
     */
    protected class CacheEntry {
        public CacheEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K key;
        public V value;
    }

    public V get(K var1) {
        LinkedNode<CacheEntry> n = this.Lookup(var1);
        if (n == null) {
            return null;
        }
        return n.getElement().value;
    }
    public void put(K var1, V var2) {
        LinkedNode<CacheEntry> n = this.Lookup(var1);
        if (n == null) {
            this.Insert(var1, var2);
        } else {
            //n.setElement(var2);
            this.Set(n, var2);
        }
    }

    public void evict(K var1) {
        LinkedNode<CacheEntry> n = this.Lookup(var1);
        if (n != null) {
            this.Delete(n);
        }
    }
    public void clear() {
        // TODO
        // Falta implementar clear en la lista
        this.elementTable.clear();
        this.numElem = 0;
    }

    /* No se ocupa aquí!
    public V Query(K var1) {
        return null;
    }
    */

    /**
     * Busca una entrada en el cache.
     * Busca una llave en la tabla hash del cache, indica su posición en la lista
     * de elementos si está cacheada.
     * @param key La llave que se busca.
     * @return Retorna una referencia al LinkedNode que contiene los datos de la
     * llave solicitada, o bien null si no está cacheada.
     */
    //protected abstract LinkedNode<CacheEntry> Lookup(K key);
    public LinkedNode<CacheEntry> Lookup(K key) {
        LinkedNode<CacheEntry> node = this.elementTable.get(key);
        return node;
    }

    /**
     * Añade una nueva entrada al caché.
     * Crea un nuevo nodo en la lista de elementos y añade una entrada en la
     * tabla hash. Si el caché está lleno elimina un elemento según la política
     * definida por el método SelectVictim.
     * @param key La llave del nuevo elemento.
     * @param value Los datos del nuevo elemento.
     * @return Una referencia al nuevo nodo creado.
     */
    //protected abstract LinkedNode<CacheEntry> Insert(K key, V value);
    public LinkedNode<CacheEntry> Insert(K key, V value) {

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

    /**
     * Sobreescribe los datos cacheados en un nodo.
     * Este método se usa para cambiar los datos asociados a una llave, sin cambiar
     * la entrada de esa llave en el hash map.
     * @param node El nodo que contiene los datos cacheados.
     * @param value El nuevo valor de los datos.
     * @return Una referencia a node.
     */
    //protected abstract LinkedNode<CacheEntry> Set(LinkedNode<CacheEntry> node, V value);
     public LinkedNode<CacheEntry> Set(LinkedNode<CacheEntry> n, V value) {
        n.getElement().value = value;
        return n;
    }

    /**
     * Selecciona el dato que se debe eliminar del caché según la
     * política de substitución. Elimina el nodo de la lista y la
     * tabla hash y retorna una referencia al mismo.
     * @return El nodo eliminado.
     */
    //protected abstract LinkedNode<CacheEntry> SelectVictim();
    protected LinkedNode<CacheEntry> SelectVictim()
    {
        LinkedNode<CacheEntry> deleted = elementList.RemoveLast();
        K key = deleted.getElement().key;
        this.elementTable.remove(key);
        this.numElem--;
        return deleted;
    }

    /**
     * Elimina una entrada del caché.
     * Se encarga de elminar el nodo dado y borrar la entrada correspondiente en la
     * tabla hash.
     * @param node El nodo a eliminar.
     */
    //protected abstract void Delete(LinkedNode<CacheEntry> node);
    public void Delete(LinkedNode<CacheEntry> node) {
        this.elementTable.remove(node.getElement().key);
        this.elementList.Remove(node);
        this.numElem--;
    }
    /**
     * El máximo número de elementos que puede haber en el caché.
     */
    protected final int size;
    protected int numElem;

    protected String name;

    protected HashMap<K,LinkedNode<CacheEntry>> elementTable;
    protected LinkedList<CacheEntry> elementList;
}
