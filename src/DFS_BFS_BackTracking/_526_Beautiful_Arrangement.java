package DFS_BFS_BackTracking;

/**
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as
 * an array that is constructed by these N numbers successfully if one of the
 * following is true for the ith position (1 <= i <= N) in this array:
 *
 * The number at the ith position is divisible by i.
 * i is divisible by the number at the ith position.
 *
 *
 * Now given N, how many beautiful arrangements can you construct?
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation:
 *
 * The first beautiful arrangement is [1, 2]:
 *
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 *
 * The second beautiful arrangement is [2, 1]:
 *
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * */


public class _526_Beautiful_Arrangement {
    private int count;
    private boolean[] visited;
    public int countArrangement(int N) {
        count = 0;
        visited = new boolean[N+1]; // visited记录1~N是否被用过，v[0]没用
        backTracking(N,1);
        return count;
    }
    private void backTracking(int N, int pos){
        // pos表示下一个开始的起点
        if(pos == N+1) {
            count++;
            return;
        }
        for(int val=1; val<=N; val++){
            if(!visited[val] && ( pos % val == 0 || val % pos == 0)) {
                //这个值可以用
                visited[val] = true;
                backTracking(N, pos+1);
                visited[val] = false;
            }
        }
    }
    public static void main(String[] args){
        _526_Beautiful_Arrangement test = new _526_Beautiful_Arrangement();
        System.out.println(test.countArrangement(2));
    }
}
