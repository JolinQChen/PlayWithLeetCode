package _AAInterviews.Roblox;
import java.util.*;
public class SocialNetwork {
    public static List<List<Integer>> socialNetwork(int[] counts) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<counts.length; i++) {
            int count = counts[i];
            if(map.containsKey(count)) map.get(count).add(i);
            else {
                map.put(count, new ArrayList<>());
                map.get(count).add(i);
            }
            if(map.get(count).size()==count) {
                res.add(map.get(count));
                map.put(count,new ArrayList<>());
            }
        }
        return res;

    }
    public static void main(String[] args) {
        int[] counts = {2,2,2,2};
        List<List<Integer>> res = socialNetwork(counts);
        for(List<Integer> list:res) {
            for(int i:list) System.out.print(i+", ");
            System.out.println();
        }
    }
}
