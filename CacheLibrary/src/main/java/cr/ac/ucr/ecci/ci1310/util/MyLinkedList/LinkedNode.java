package cr.ac.ucr.ecci.ci1310.util.MyLinkedList;

public class LinkedNode<E> {

    public LinkedNode() {
        this.element = null;
    }

    public LinkedNode(E element) {
        this.element = element;
    }

    public E getElement() {
        return this.element;
    }
    public void setElement(E element) {
        this.element = element;
    }

    public LinkedNode<E> Next() {
        return this.next;
    }
    public LinkedNode<E> Prev() {
        return this.prev;
    }

    private E element;
    protected LinkedNode<E> next;
    protected LinkedNode<E> prev;

}
