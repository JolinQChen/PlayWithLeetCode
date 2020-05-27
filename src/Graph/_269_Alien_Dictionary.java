package Graph;

import java.util.*;

/**
 * You receive a list of non-empty words from the dictionary,
 * where words are sorted lexicographically by the rules of this new
 * language. Derive the order of letters in this language.
 *
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * Output: "wertf"
 * */


public class _269_Alien_Dictionary {
    public String alienOrder_1(String[] words) {
        //1. build a digraph with all possible chars
        //2. topological sort
        //3. if there is a cycle then return ""
        //4. otherwise return reverse - topologic order
        //instead - we can also build a reverse graph and then return topological order
        Map<Character,List<Character>> reverseDigraph = new HashMap<>();
        Map<Character,Integer> seen = new HashMap<>();
        for(String word: words) {
            for(char ch: word.toCharArray()) {
                reverseDigraph.putIfAbsent(ch, new ArrayList<>());
            }
        }
        int n = words.length;
        for(int i=1; i < n; i++) {
            String prev = words[i-1];
            String curr = words[i];

            //in the below condition prev.startsWith(curr) takes care of situations like prev=abcd and curr = ab - this is invalid as specified in one of the solution
            //this is also invalid intituively

            //the lengths are required, otherwise words with same-length will give us false negative

            if(prev.startsWith(curr) && prev.length() > curr.length()) return "";



            //find the first difference
            int min = Math.min(prev.length(),curr.length());
            for(int j=0; j < min; j++) {
                if(prev.charAt(j) != curr.charAt(j)) {
                    //prev-char to curr-char is the edge
                    //but remember that we are forming revese graph so reverse it
                    reverseDigraph.get(curr.charAt(j)).add(prev.charAt(j));
                    break;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for(char ch: reverseDigraph.keySet()) {
            if(!seen.containsKey(ch)) {
                boolean cycleExists = dfs(reverseDigraph,ch,seen,result);
                if(cycleExists) {
                    return "";
                }
            }
        }



        return result.toString();
    }

    private boolean dfs(Map<Character,List<Character>> reverseDigraph, char curr,Map<Character,Integer> seen, StringBuilder result) {
        seen.put(curr,0);

        if(reverseDigraph.containsKey(curr)) {

            List<Character> neighbors = reverseDigraph.get(curr);
            for(char next: neighbors) {

                if(seen.containsKey(next) && seen.get(next) == 0) {
                    return true; //cycle detected
                } else if(!seen.containsKey(next)) {

                    if(dfs(reverseDigraph,next,seen,result)) {
                        return true;
                    }

                }


            }

        }


        result.append(curr);
        seen.put(curr,1);
        return false;
    }




    public static String alienOrder(String[] words) {
        // 1.build DAG
        //Map<Character, List<Character>> reversedDAG = new HashMap<>();
        Map<Character, Set<Character>> DAG = new HashMap<>();
        Map<Character, Integer> indegreeMap = new HashMap<>();
        int count = 0;
        for(String word:words){
            for(char c:word.toCharArray()){
                if(!DAG.containsKey(c)) {
                    DAG.put(c,new HashSet<>());
                    indegreeMap.put(c,0);
                    count++;
                    System.out.println("count: "+count);
                }

            }
        }
        int n = words.length;
        for(int i=1; i < n; i++) {
            String prev = words[i-1];
            String curr = words[i];
            if(prev.startsWith(curr) && prev.length() > curr.length())
                return "";
            //find the first difference
            int min = Math.min(prev.length(),curr.length());
            for(int j=0; j < min; j++) {
                if(prev.charAt(j) != curr.charAt(j)) {
                    //prev-char to curr-char is the edge
                    if(!DAG.get(prev.charAt(j)).contains(curr.charAt(j)))
                        indegreeMap.put(curr.charAt(j),indegreeMap.get(curr.charAt(j))+1);
                    DAG.get(prev.charAt(j)).add(curr.charAt(j));
                    break;
                }
            }
        }
        String res = "";
        Stack<Character> stack = new Stack<>();

        for(char key:indegreeMap.keySet()){
            if(indegreeMap.get(key)==0) {
                stack.push(key);

            }
        }
        while(!stack.isEmpty()) {
            char c = stack.pop();
            res += c;
            //System.out.println(c + " : "+count);
            count--;
            for(char next:DAG.get(c)){
                indegreeMap.put(next, indegreeMap.get(next)-1);
                if(indegreeMap.get(next)==0) {
                    stack.push(next);
                }
            }
        }
        if(count!=0) return "";

        return res;
    }

    public static void main(String[] args){
        String[] input = {"wrt","wrf","er","ett","rftt"};
        String res = alienOrder(input);
        System.out.println(res);
    }

}
