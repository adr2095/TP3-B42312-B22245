package cr.ac.ucr.ecci.ci1310.cache.algorithm

class LeastRecentlyUsedTest extends GroovyTestCase {

    void testLookup() {
        def cash = new LeastRecentlyUsed<Integer, String>()
        assert cash.Lookup(15) == null
    }

    void testInsert() {
        def cash = new LeastRecentlyUsed<Integer, String>()

        def testVals = [716: "Daniel Diaz Molina", 770776: "Nissan Tida", 22245: "Carnet B"]

        assert cash.Lookup(716) == null
        testVals.each { k, v ->
            println "${k} , ${v}"
            assert cash.Lookup(k) == null
            cash.Insert(k, v)
        }

        testVals.each { k, v ->
            assert cash.Lookup(k).getElement().value == v
            assert cash.Lookup(k).getElement().key == k
        }

    }

    void testSet() {
        def cash = new LeastRecentlyUsed<Integer, String>()

        def testVals = [716: "Daniel Diaz Molina", 770776: "Nissan Tida", 22245: "Carnet B"]

        testVals.each { k, v ->
            cash.Insert(k, v)
        }

        cash.Set(cash.Lookup(770776), "Subaru Impressa")

        testVals.each { k, v ->
            if (k == 770776) {
                assertFalse cash.Lookup(k).getElement().value == v
            } else {
                assert cash.Lookup(k).getElement().value == v
            }
            assert cash.Lookup(k).getElement().key == k
        }


    }

    void testSelectVictim() {

        def cash = new LeastRecentlyUsed<Integer, String>()

        def testVals = [716: "Daniel Diaz Molina", 770776: "Nissan Tida", 666: "Number of the Beast", 1984: "Fahrenheit", 22245: "Carnet B"]

        testVals.each { k, v ->
            cash.Insert(k, v)
        }

        String last = testVals.get(770776);


        assert cash.Lookup(716) != null
        def node = cash.SelectVictim()
        assert node.getElement().value == last
        assert cash.Lookup(770776) == null

        node = cash.SelectVictim()
        assert node.getElement().value == "Number of the Beast"
    }

    void testDelete() {
        def cash = new LeastRecentlyUsed<Integer, String>()

        def testVals = [716: "Daniel Diaz Molina", 770776: "Nissan Tida", 666: "Number of the Beast", 1984: "Fahrenheit", 22245: "Carnet B"]

        testVals.each { k, v ->
            cash.Insert(k, v)
        }

        assert cash.Lookup(666) != null
        def node = cash.Lookup(666)
        def prev = node.Prev()
        def next = node.Next()
        cash.Delete(node);
        assert cash.Lookup(666) == null
        if(next != null) {
            assert next.Prev() == prev
        }

        if(prev != null) {
            assert prev.Next() == next
        }
    }
}
