package _AAInterviews.Amazon;

import java.util.*;
/**
 * Given a list of reviews, a list of keywords and an integer k. Find the most popular k keywords in order of most to least frequently mentioned.
 *
 * The comparison of strings is case-insensitive.
 * Multiple occurances of a keyword in a review should be considred as a single mention.
 * If keywords are mentioned an equal number of times in reviews, sort alphabetically.
 * */
public class TopKFrequentlyMentionedKeywords {
    private static List<String> solve(int k, String[] keywords, String[] reviews) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(keywords));
        for(String review : reviews) {
            String[] strs = review.split("\\W");
            // 或许需要加一个&& !s.equals("")
            Set<String> added = new HashSet<>();
            for(String s:strs) {
                s = s.toLowerCase();
                if(!added.contains(s) && set.contains(s)) {
                    added.add(s);
                    map.put(s,map.getOrDefault(s,0)+1);
                }
            }
        }
        PriorityQueue<String> pq = new PriorityQueue<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {

                        return map.get(o1).equals(map.get(o2)) ? o2.compareTo(o1) : map.get(o1)-map.get(o2);
                    }
                }
        );

        for(String key:map.keySet()) {
            pq.add(key);
            if(pq.size()>k) pq.poll();
        }
        while (pq.size()>0) {
            res.add(pq.poll());
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        String[] keywords = {"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"};
        String[] reviews = {
                "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services",
                "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell",
                "Betacellular is better than deltacellular.",
        };
        int k = 2;
        List<String> res = solve(k, keywords, reviews);
        for(String str:res) System.out.println(str);
        //System.out.println("sa".compareTo("ss"));
    }
}
