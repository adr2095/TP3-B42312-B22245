package cr.ac.ucr.ecci.ci1310.test.cr.ac.ucr.ecci.ci1310.cache.algorithm

import cr.ac.ucr.ecci.ci1310.cache.algorithm.FirstInFirstOut

class FirstInFirstOutTest extends GroovyTestCase {

        void testLookup() {
            def cash = new FirstInFirstOut<Integer, String>()
            assert cash.Lookup(15) == null
        }

        void testInsert() {
            def cash = new FirstInFirstOut<Integer, String>()

            def testVals = [716: "Daniel Diaz Molina", 770776: "Nissan Tida", 22245: "Carnet B"]

            assert cash.Lookup(716)  == null
            testVals.each { k , v ->
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
            def cash = new FirstInFirstOut<Integer, String>()

            def testVals = [716: "Daniel Diaz Molina", 770776: "Nissan Tida", 22245: "Carnet B"]

            testVals.each { k , v ->
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

            def cash = new FirstInFirstOut<Integer, String>()

            def testVals = [716: "Daniel Diaz Molina", 770776: "Nissan Tida", 666: "Number of the Beast", 1984: "Fahrenheit", 22245: "Carnet B"]

            testVals.each { k , v ->
                cash.Insert(k, v)
            }

            testVals.forEach { k , v ->
                assert cash.Lookup(k) != null
                def node = cash.SelectVictim()
                assert node.getElement().key == k
                assert node.getElement().value == v
                assert cash.Lookup(k) == null
            }


        }

        void testDelete() {
            def cash = new FirstInFirstOut<Integer, String>()

            def testVals = [716: "Daniel Diaz Molina", 770776: "Nissan Tida", 666: "Number of the Beast", 1984: "Fahrenheit", 22245: "Carnet B"]

            testVals.each { k , v ->
                cash.Insert(k, v)
            }

            assert cash.Lookup(666) != null
            def node = cash.Lookup(666)
            def prev = node.Prev()
            def next = node.Next()
            cash.Delete(node);
            assert cash.Lookup(666) == null
            assert next.Prev() == prev
            assert prev.Next() == next
        }
}
