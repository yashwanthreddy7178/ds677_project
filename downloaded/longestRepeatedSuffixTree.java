public abstract class longestRepeatedSuffixTree extends CompressedTrie {

    protected int maxLength;
    protected SuffixTreeNode substringStartNode;

    /**
     * Constructs a suffix tree containing all suffices of a single word.
     * A dollar sign is appended to the end of the word
     * @param word The word to create a tree of all its suffices
     */
    public longestRepeatedSuffixTree(String word) {
        super();
        char[] wordChar = (word + "$").toCharArray();
        for (int i = 0; i< wordChar.length; i++)
            addSuffix(wordChar, i);
        compressTree();
        createLongestRepeatedSubstring(); 
    }

    /**
     * Calculates and returns the longest repeated substring in the tree's word.
     * In case of multiple longest Repeated Substring we choose the longest Repeated Substring which comes 1st lexicographically.
     * This function preforms placement to the maxLength and substringStartNode members.
     * Examples: new longestRepeatedSuffixTreeImpl("mississippi").getLongestRepeatedSubstring() -> "issi",
     * new longestRepeatedSuffixTreeImpl("ioiosbdbd").getLongestRepeatedSubstring() -> "bd",
     * new longestRepeatedSuffixTreeImpl("bdioiosbd").getLongestRepeatedSubstring() -> "bd",
     * new longestRepeatedSuffixTreeImpl("abcd").getLongestRepeatedSubstring() -> "X"  - no repeated substring,
     * new longestRepeatedSuffixTreeImpl("a").getLongestRepeatedSubstring() -> "X"  - no repeated substring,
     * new longestRepeatedSuffixTreeImpl("aaaaaaaaaa").getLongestRepeatedSubstring() -> "aaaaaaaaa"
     */
    public abstract void createLongestRepeatedSubstring();

    /**
     * Getter for the longest repeated substring
     * You should use the substringStartNode you already found in createLongestRepeatedSubstring function.
     * @return the longest repeated substring
     */
    public abstract String getLongestRepeatedSubstring();

    /**
     * Getter for the length of the longest repeated substring
     * @return length of the longest repeated substring
     */
    public int getLongestRepeatedSubstringLength()
    {
        return maxLength;
    }
}
