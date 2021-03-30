import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayDequeTest {
    @Test
    public void testIsEmpty(){
        ArrayDeque<Integer> input = new ArrayDeque<>();
        assertTrue(input.isEmpty());
        assertEquals(0,input.size());
    }
    @Test
    public void testAdd(){
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i=1;i<=10;i++){
            input.addFirst(i);
        }
        for (int i=1;i<=10;i++){
            input.addLast(i);
        }
        assertFalse(input.isEmpty());
        assertEquals(20, input.size());
    }
    @Test
    public void testRemove(){
        ArrayDeque<Integer> input = new ArrayDeque<>();
        input.addFirst(1);
        input.addLast(2);
        input.addLast(3);
        input.removeFirst();
        input.removeLast();
        assertFalse(input.isEmpty());
        assertEquals(1, input.size());
    }
    @Test
    public void testGet(){
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i=1;i<=10;i++){
            input.addFirst(i);
        }
        for (int i=1;i<=10;i++){
            input.addLast(i);
        }
        assertFalse(input.isEmpty());
        assertEquals(20, input.size());
        int actual0=input.get(0);
        input.removeLast();
        int actual19=input.get(18);
        assertEquals(10, actual0);
        assertEquals(9, actual19);

    }
}