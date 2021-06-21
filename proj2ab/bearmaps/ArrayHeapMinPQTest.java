package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(10, 0.5);
        test.add(11, 2);
        test.add(100, 0.2);
        test.add(80, 0.5);
        assertEquals(100, (int) test.getSmallest());
    }

    @Test
    public void testContain() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(10, 0.5);
        test.add(11, 2);
        test.add(100, 0.2);
        test.add(80, 0.5);
        assertTrue(test.contains(10));
        assertTrue(test.contains(100));
        assertFalse(test.contains(1));
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> t = new ArrayHeapMinPQ<>();
        t.add(10, 1);
        t.add(20, 3);
        t.add(30, 2);
        t.add(100, 0.5);
        t.add(25, 4);
        t.add(55, 1.5);
        assertEquals(100, (int) t.removeSmallest());
        assertEquals(10, (int) t.getSmallest());
    }

    @Test
    public void testChangePriority(){
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(10, 0.6);
        test.add(11, 2);
        test.add(100, 0.2);
        test.add(80, 0.5);
        test.changePriority(100,1);
        test.changePriority(11,0.01);
        assertEquals(11,(int)test.getSmallest());
    }
    @Test
    public void testRuntime(){

    }
}
