package _AAInterviews.Wish;

import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import java.util.*;
public class Relative {
    private Map<String, Map<String, String>> map;
    List<String> path;
    /** Assume we don't have duplicate in the relationships between two person, and don't come back (A->D->A)*/
    public List<String> getRelationSequence(String[][] relationships, String name1, String name2) {
        map = new HashMap<>(); // key = name1, value = (key:name2, value:relationship)
        path = new ArrayList<>();
        for(String[] pair : relationships) {
            if(!map.containsKey(pair[0])) map.put(pair[0], new HashMap<>());
            map.get(pair[0]).put(pair[2],pair[1]);
        }
        Set<String> set = new HashSet<>();
        set.add(name1);
        dfs(name1, name2, name1, set);
        return path;
    }

    private void dfs(String currName, String targetName, String currPath, Set<String> visited) {
        if(currName.equals(targetName)) {
            path.add(currPath);
            return;
        }
        if(!map.containsKey(currName)) return;
        Map<String, String> next = map.get(currName);
        for(String nextName:next.keySet()) {
            if(!visited.contains(nextName)) {
                visited.add(nextName);
                dfs(nextName, targetName, currPath+" "+next.get(nextName)+" "+nextName, visited);
                visited.remove(nextName);
            }
        }
    }


    /**
Follow-up: Allow duplicated relations between two people -> add a nested for-loop
 */
    Map<String, Map<String, List<String>>> map_followUp;
    public List<String> getRelationSequence_duplicated(String[][] relations, String name1, String name2){
        map_followUp = new HashMap<>();
        path = new ArrayList<>();
        for(String[] pair:relations) {
            if(!map_followUp.containsKey(pair[0])) map_followUp.put(pair[0], new HashMap<>());
            if(!map_followUp.get(pair[0]).containsKey(pair[2])) map_followUp.get(pair[0]).put(pair[2], new ArrayList<>());
            map_followUp.get(pair[0]).get(pair[2]).add(pair[1]);
        }
        Set<String> visited = new HashSet<>();
        visited.add(name1);
        dfs_followUp(name1, name2, name1, visited);
        return path;
    }

    private void dfs_followUp(String currName, String targetName, String currPath, Set<String> visited) {
        if(currName.equals(targetName)) {
            path.add(currPath);
            return;
        }
        if(!map_followUp.containsKey(currName)) return;
        Map<String, List<String>> nextMap = map_followUp.get(currName);
        for(String nextName:nextMap.keySet()) {
            if(!visited.contains(nextName)) {
                visited.add(nextName);
                List<String> tmpRelations = nextMap.get(nextName);
                for(String relation:tmpRelations) {
                    dfs_followUp(nextName, targetName, currPath+" "+relation+" "+nextName, visited);
                }
                visited.remove(nextName);
            }
        }
    }

    public static void main(String[] args) {
        Relative obj = new Relative();

        String[][] relations = {
                {"Bart", "brother", "Lisa"},
                {"Bart", "son", "Homer"},
                {"Marge", "wife", "Homer"},
                {"Lisa", "daughter", "Homer"},
                {"Homer", "father", "Bart"}   // form a cycle  -> no problem. Has a visited set.
        };

        String[][] relations_followUp = {{"Bart", "sister", "Lisa"},
        {"Bart", "brother", "Lisa"},
        {"Bart", "son", "Homer"},
        {"Marge", "wife", "Homer"},
        {"Lisa", "daughter", "Homer"},
        {"Lisa", "son", "Homer"}};
        List<String> res = obj.getRelationSequence(relations, "Bart","Homer");
        List<String> res2 = obj.getRelationSequence_duplicated(relations_followUp,"Bart","Homer");
        for(String s:res) System.out.println(s);
        System.out.println();
        for (String s:res2) System.out.println(s);

    }
}
