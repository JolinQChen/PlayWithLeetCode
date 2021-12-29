package _All;
import java.util.*;

/**
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1807"
 *   |
 * "7810"
 * */

public class _299_Bulls_and_Cows {
    public String getHint(String secret, String guess) {
        Map<Character, Set<Integer>> secretMap = new HashMap<>(); // digit-set(count,position)
        Map<Character, Integer> secretMapCount = new HashMap<>();
        int cow = 0; // right number in wrong pos
        int bull = 0; // right number in right pos
        char[] guessList = guess.toCharArray();
        int length = secret.length();
        for(int i=0; i<length; i++) {
            char curr = secret.charAt(i);
            if(!secretMap.containsKey(curr)) {
                secretMap.put(curr, new HashSet<>());
                secretMapCount.put(curr, 0);
            }
            secretMap.get(curr).add(i);
            secretMapCount.put(curr, secretMapCount.get(curr)+1);
        }
        for(int i=0; i<length; i++) {
            char curr = guessList[i];
            if(secretMap.containsKey(curr)) {
                if(secretMap.get(curr).contains(i)) {
                    bull++;
                    if(secretMapCount.get(curr)==0) {
                        cow--;
                    } else {
                        secretMapCount.put(curr, secretMapCount.get(curr)-1);
                    }
                }
                else {
                    if(secretMapCount.get(curr)>0) {
                        cow++;
                        secretMapCount.put(curr, secretMapCount.get(curr)-1);
                    }
                }
            }
        }
        String res = bull+"A"+cow+"B";
        return res;
    }
}
