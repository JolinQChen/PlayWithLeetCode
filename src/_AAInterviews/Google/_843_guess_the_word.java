package _AAInterviews.Google;
import java.util.*;

public class _843_guess_the_word {
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> words = new ArrayList<>(Arrays.asList(wordlist));
        Random rd = new Random();
        for(int i=0; i<10; i++) {
            String guessWord = words.get(rd.nextInt(words.size()));
            int guessValue = master.guess(guessWord);
            // found it
            if(guessValue == 6) return;
            // found word with the same matched chars
            words = findMatchWords(words, guessWord, guessValue);
        }
    }

    private List<String> findMatchWords(List<String> words, String guessWord, int sameChar) {
        List<String> res = new ArrayList<>();
        words.stream().forEach(word -> {
            if(countMatch(word, guessWord) == sameChar) res.add(word);
        });
        return res;
    }

    private int countMatch(String word1, String word2) {
        int count = 0;
        for(int i=0; i<6;i++) {
            if(word1.charAt(i)==word2.charAt(i)) count++;
        }
        return count;
    }
}
