import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static CharacterComparator offBy0 = new OffByN(0);
    static CharacterComparator offBy1 = new OffByN(1);
    static CharacterComparator offBy2 = new OffByN(2);

    @Test
    public void testEqualCharsOffByN() {
        assertTrue(offBy0.equalChars('a', 'a'));
        assertTrue(offBy0.equalChars('b', 'b'));
        assertFalse(offBy0.equalChars('a', 'b'));
        assertFalse(offBy0.equalChars('b', 'a'));

        assertTrue(offBy1.equalChars('a', 'b'));
        assertTrue(offBy1.equalChars('b', 'a'));
        assertTrue(offBy1.equalChars('%', '&'));
        assertFalse(offBy1.equalChars('a', 'a'));
        assertFalse(offBy1.equalChars('b', 'b'));
        assertFalse(offBy1.equalChars('a', 'c'));
        assertFalse(offBy1.equalChars('c', 'a'));

        assertTrue(offBy2.equalChars('a', 'c'));
        assertTrue(offBy2.equalChars('c', 'a'));
        assertFalse(offBy2.equalChars('%', '&'));
        assertFalse(offBy2.equalChars('%', '&'));
        assertFalse(offBy2.equalChars('a', 'a'));
        assertFalse(offBy2.equalChars('a', 'b'));
        assertFalse(offBy2.equalChars('b', 'a'));
    }
}
