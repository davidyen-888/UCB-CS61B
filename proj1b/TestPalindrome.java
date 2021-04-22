import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testIsPalindrome(){
        String s0="";
        String s1="a";
        String sEvenTrue="noon";
        String sEvenFalse="tree";
        String sOddTrue="racecar";
        String sOddFalse="horse";
        assertTrue(palindrome.isPalindrome(s0));
        assertTrue(palindrome.isPalindrome(s1));
        assertTrue(palindrome.isPalindrome(sEvenTrue));
        assertFalse(palindrome.isPalindrome(sEvenFalse));
        assertTrue(palindrome.isPalindrome(sOddTrue));
        assertFalse(palindrome.isPalindrome(sOddFalse));
    }
    static CharacterComparator offByOne=new OffByOne();
    @Test
    public void testIsPalindromeOffByOne(){
        String s0="";
        String s1="a";
        String sEvenTrue="acdb";
        String sEvenFalse="abcd";
        String sOddTrue="flake";
        String sOddFalse="faake";
        assertTrue(palindrome.isPalindrome(s0, offByOne));
        assertTrue(palindrome.isPalindrome(s1, offByOne));
        assertTrue(palindrome.isPalindrome(sEvenTrue, offByOne));
        assertFalse(palindrome.isPalindrome(sEvenFalse, offByOne));
        assertTrue(palindrome.isPalindrome(sOddTrue, offByOne));
        assertFalse(palindrome.isPalindrome(sOddFalse, offByOne));
    }
}