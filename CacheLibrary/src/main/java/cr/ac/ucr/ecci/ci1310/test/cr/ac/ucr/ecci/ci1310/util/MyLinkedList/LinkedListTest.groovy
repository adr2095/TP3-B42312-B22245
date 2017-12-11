package cr.ac.ucr.ecci.ci1310.util.MyLinkedList

class LinkedListTest extends GroovyTestCase {

    void testAddFirst() {
        def list = new LinkedList<Integer>()

        assert list.getFirst() == null
        assert list.getLast() == null
        assert list.NumElem() == 0

        list.addFirst(10)
        assert list.getFirst().getElement() == 10
        assert list.getLast().getElement() == 10
        assert list.getLast() == list.getFirst()
        assert list.NumElem() == 1

        list.addFirst(15)
        assert list.getFirst().getElement() == 15
        assert list.getLast().getElement() == 10
        assert list.getFirst() != list.getLast()
        assert list.NumElem() == 2

        list.addFirst(25)
        assert list.getFirst().getElement() == 25
        assert list.getLast().getElement() == 10
        assert list.NumElem() == 3
        assert list.getFirst().Next().getElement() == 15
        assert list.getLast().Prev().getElement() == 15
    }

    void testAddLast() {
        def list = new LinkedList<Integer>()

        assert list.getLast() == null
        assert list.getFirst() == null
        assert list.NumElem() == 0

        list.addLast(10)
        assert list.getLast().getElement() == 10
        assert list.getFirst().getElement() == 10
        assert list.getFirst() == list.getLast()
        assert list.NumElem() == 1

        list.addLast(15)
        assert list.getLast().getElement() == 15
        assert list.getFirst().getElement() == 10
        assert list.getFirst() != list.getLast()
        assert list.NumElem() == 2

        list.addLast(25)
        assert list.getLast().getElement() == 25
        assert list.getFirst().getElement() == 10
        assert list.NumElem() == 3
        assert list.getLast().Prev().getElement() == 15
        assert list.getFirst().Next().getElement() == 15
    }

    void testRemoveFirst() {

        def list = new LinkedList<Integer>()

        def elementos = [10, 11, 12, 13, 14, 15]

        elementos.each {
            list.addLast(it)
        }

        while (elementos.size() > 0) {

            LinkedNode<Integer> it = list.getFirst()
            elementos.each { i ->
                assert it.getElement() == i
                it = it.Next()
            }

            it = list.getLast()
            elementos.reverseEach { i->
                assert it.getElement() == i
                it = it.Prev()
            }

            assert list.NumElem() == elementos.size()
            assert list.getFirst().Prev() == null
            assert list.getLast().Next() == null
            elementos.removeAt(0)
            list.RemoveFirst()
        }
        assert list.NumElem() == 0
        assert list.getFirst() == null
        assert list.getLast() == null

    }

    void testRemoveLast() {

        def list = new LinkedList<Integer>()

        def elementos = [10, 11, 12, 13, 14, 15]

        elementos.reverseEach {
            list.addFirst(it)
        }

        while (elementos.size() > 0) {

            LinkedNode<Integer> it = list.getFirst()
            elementos.each { i ->
                assert it.getElement() == i
                it = it.Next()
            }

            it = list.getLast()
            elementos.reverseEach { i->
                assert it.getElement() == i
                it = it.Prev()
            }

            assert list.NumElem() == elementos.size()
            assert list.getFirst().Prev() == null
            assert list.getLast().Next() == null
            elementos.removeAt(elementos.size()-1)
            list.RemoveLast()
        }
        assert list.NumElem() == 0
        assert list.getFirst() == null
        assert list.getLast() == null
    }

    void testRemove() {

        def list = new LinkedList<Integer>()

        def elementos = [10, 11, 12, 13, 14, 15, 24, -1, 0, 69]

        elementos.each {
            list.addLast(it)
        }

        LinkedNode<Integer> it
        for (int j = 0; j < 6; j++) {

            if (j%3 == 0) {
                elementos.removeAt(2);
                it = list.getFirst()
                it = it.Next();
                it = it.Next();
                list.Remove(it);
            }

            if (j%3 == 1) {
                elementos.removeAt(0);
                it = list.getFirst()
                list.Remove(it)
            }

            if (j%3 == 2) {
                elementos.removeAt(elementos.size()-1);
                it = list.getLast()
                list.Remove(it)
            }

            it = list.getFirst()
            elementos.each { i ->
                assert it.getElement() == i
                it = it.Next()
            }

            it = list.getLast()
            elementos.reverseEach { i->
                assert it.getElement() == i
                it = it.Prev()
            }

            assert list.NumElem() == elementos.size()
            assert list.getFirst().Prev() == null
            assert list.getLast().Next() == null

        }

    }
}
