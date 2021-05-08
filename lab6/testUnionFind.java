import org.junit.Test;

import static org.junit.Assert.*;

public class testUnionFind {
    @Test
    public void testConnect() {
        UnionFind test = new UnionFind(5);

        assertFalse(test.connected(1, 2));
        test.union(1, 2);
        assertTrue(test.connected(1, 2));
        test.union(3, 4);
        assertTrue(test.connected(3, 4));
        assertFalse(test.connected(2, 3));
        test.union(2, 3);
        assertTrue(test.connected(2, 3));
    }

    @Test
    public void testSizeOf() {
        UnionFind test = new UnionFind(8);

        assertEquals(1, test.sizeOf(1));
        assertEquals(-1, test.parent(1));
    }
}
