import java.util.*;
class Solution {

    long modulus = (long)Math.pow(2, 32);
    long a = 26;

    public String longestDupSubstring(String text) {
        
        int[] textAsInt =  new int[text.length()];
        for(int i = 0; i < textAsInt.length; i++) {
            textAsInt[i] = (int)text.charAt(i) - (int)'a';
        }

        int left = 1, right = text.length();

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(search(mid, textAsInt) != -1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int index = search(left - 1, textAsInt);

        return index != -1 ? text.substring(index, index + left - 1) : "";
    }

    public int search(int mid, int[] textAsInt) {

        HashSet<Long> hashCollection = new HashSet<>();
        long  currentHash = 0;

        for(int i = 0; i < mid; i++) {
            currentHash = (currentHash * a + textAsInt[i]) % modulus;
        }

        hashCollection.add(currentHash);

        long rollingHashConst = 1L;

        for (int i = 1; i <= mid; i++) {
            rollingHashConst = (rollingHashConst * a) % modulus;
        }

        for(int i = 1; i < textAsInt.length - mid + 1; i++) {
            currentHash = (currentHash * a - textAsInt[i - 1] * rollingHashConst % modulus + modulus) % modulus;
            currentHash = (currentHash + textAsInt[i + mid - 1]) % modulus;
            if(hashCollection.contains(currentHash)) {
                return i;
            }

            hashCollection.add(currentHash);
        }

        return -1;
    }
}

public class June19 {
    public static void main(String args[]) {
        System.out.println( new Solution().longestDupSubstring("banana"));
    }

}