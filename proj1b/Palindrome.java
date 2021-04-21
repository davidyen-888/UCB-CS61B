public class Palindrome {
    /**
     * Given a String,
     * returns a Deque where the characters appear in the same order as in the String.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (first == last) {
                return isPalindromeHelper(deque);
            } else {
                return false;
            }
        }
    }

    /** Returns true if the given String is a palindrome, and false otherwise. */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }

    /**
     * Returns true if the word is a palindrome according to the character
     * comparison test provided by the CharacterComparator passed in as argument cc.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return true;
    }
}
