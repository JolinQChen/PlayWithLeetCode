public class _243_Shortest_Word_Distance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int pos1 = -1;
        int pos2 = -1;
        int minDist = Integer.MAX_VALUE;
        int len = words.length;
        for(int i=0; i<len; i++){
            if(words[i].equals(word1)) {
                pos1 = i;
                if(pos2>=0) minDist = Math.min(pos1 - pos2, minDist);
            }
            else if(words[i].equals(word2)) {
                pos2 = i;
                if(pos1>=0) minDist = Math.min(pos2-pos1, minDist);
            }
        }
        return minDist;
    }
}
