package _AAInterviews.Google.test;

import _AAInterviews.Google.AutoCompleteSystem;
import org.junit.Test;

import java.util.List;

public class AutoCompleteSystemTest {
    @Test
    public void test(){
        String[] sentences = new String[]{"i love you","island","iroman","i love leetcode"};
        int[] times = new int[]{5,3,2,2};
        AutoCompleteSystem autocompleteSystem = new AutoCompleteSystem(sentences, times);
        List<String> res = autocompleteSystem.input('i');
        System.out.println(res);

    }
}
