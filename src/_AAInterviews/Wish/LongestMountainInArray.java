package _AAInterviews.Wish;

public class LongestMountainInArray {
    public static int longestMountain(int[] A) {
        int longest = 0;
        int idx = 1;
        while (idx<A.length) {
            if(A[idx]<=A[idx-1]) idx++;
            else {
                int start = idx;
                while (idx<A.length && A[idx]>A[idx-1]) idx++;
                if(idx==A.length || A[idx]==A[idx-1]) continue;
                while (idx<A.length && A[idx]<A[idx-1]) idx++;
                longest = Math.max(idx-start+1, longest);
            }
        }
        return longest;
    }

    public static void main(String[] args){
        int[] A = {2,1,4,7,3,2,5};
        System.out.println(longestMountain(A));

    }

}
