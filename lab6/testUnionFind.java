import org.junit.Test;

import static org.junit.Assert.*;

public class testUnionFind {
    @Test
    public void testConnect() {
        UnionFind test = new UnionFind(15);

        assertFalse(test.connected(1, 2));
        test.union(1, 2);
        assertTrue(test.connected(1, 2));
        test.union(3, 4);
        assertFalse(test.connected(2, 3));
        test.union(2, 3);
        assertEquals(4, test.parent(2));
        assertEquals(4, test.sizeOf(4));
        test.union(1, 3);
        assertEquals(4, test.parent(1));    // Path-compression
        test.union(2, 5);
        test.union(0, 2);
        assertEquals(4, test.parent(0));
    }
}
