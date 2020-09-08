package _AAInterviews.Wish;
import java.util.*;

/**
 *
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 *
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.
 * (The websites in a 3-sequence are not necessarily distinct.)
 *
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically
 * smallest such 3-sequence.
 *
 * Input:
 * username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
 * timestamp = [1,2,3,4,5,6,7,8,9,10],
 * website = ["home","about","career","home","cart","maps","home","home","about","career"]
 *
 * Output: ["home","about","career"]
 * */
public class _1152_AnalyzeUserWebsiteVisitPattern {
    class time_web {
//        String username;
        int timestamp;
        String website;
        public time_web(int ts, String ws) {
//            username = un;
            timestamp = ts;
            website = ws;
        }
    }
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<String> res = new ArrayList<>();
        String finalPattern = "";
        int count = 0;
        Map<String, Set<String>> countUserMap = new HashMap<>(); // string made of 3 websites, set contains all users
        Map<String, List<time_web>> userPatternMap = new HashMap<>(); // string for username, list for time-web pair
        for(int i=0; i<username.length; i++) {
            if(!userPatternMap.containsKey(username[i])) userPatternMap.put(username[i], new ArrayList<>());
            userPatternMap.get(username[i]).add(new time_web(timestamp[i], website[i])); // see if we need to sort this list
        }




        for(String user:username) {
            List<time_web> tmp = userPatternMap.get(user);
            int size = tmp.size();
            if(size>=3) {
                // we've find at least one pattern
//                int first = 0, second = 1, third = 2;
                Collections.sort(tmp, new Comparator<time_web>() {
                    @Override
                    public int compare(time_web o1, time_web o2) {
                        return o1.timestamp-o2.timestamp;
                    }
                });
                for(int first = 0; first<size-2; first++) {
                    for(int sec = first+1; sec<size-1; sec++) {
                        for(int third = sec+1; third<size; third++) {
                            String tmpPattern = tmp.get(first).website + "," + tmp.get(sec).website + "," +  tmp.get(third).website;
                            if(!countUserMap.containsKey(tmpPattern)) countUserMap.put(tmpPattern, new HashSet<>());
                            countUserMap.get(tmpPattern).add(user);
                        }
                    }
                }
            }


        }
        for(String pattern:countUserMap.keySet()) {
            System.out.println(pattern + " , " + countUserMap.get(pattern));
            if(countUserMap.get(pattern).size()>count) {
                count = countUserMap.get(pattern).size();
                finalPattern = pattern;
            }
            else if(countUserMap.get(pattern).size()==count) {
                if(finalPattern.compareTo(pattern)>0) finalPattern = pattern;
            }
        }

        String[] restmp = finalPattern.split(",");
        return Arrays.asList(restmp);
    }

    public static void main(String[] args) {
        String[] username = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
        int[] timestamp = {1,2,3,4,5,6,7,8,9,10};
        String[] website = {"home","about","career","home","cart","maps","home","home","about","career"};

        _1152_AnalyzeUserWebsiteVisitPattern test = new _1152_AnalyzeUserWebsiteVisitPattern();
        List<String> res = test.mostVisitedPattern(username, timestamp, website);
        for(String s:res) System.out.println(s);
    }
}
