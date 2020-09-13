package _AAInterviews.Citrix.FullTime;

public class MaximumDifferenceWhereLagerNumberAppearsAfterSmallerOne {
    public static int solution(int[] arr) {
        int res = -1;
        int min = arr[0];
        for(int i=1; i<arr.length; i++) {
            if(arr[i]<min) min = arr[i];
            else res = Math.max(res, arr[i]-min);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = {6,3,1,2,27,5,7,8,2};
        System.out.println(solution(arr));
    }
}
