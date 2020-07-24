package _AAInterviews.Citrix;

//应该不是最优解
public class CircularArray {

    public static int findMostVisitedSmallest(int n, int[] endNodes){
        if(endNodes==null || endNodes.length==0) return 0;
        int m = endNodes.length;
        if(m==1) return endNodes[0];
        int[] count = new int[m+1];
        for(int i=0;i<m-1; i++){
            int left = endNodes[i];
            int right = endNodes[i+1];
            if(left==right) count[left]++;
            else if(left<right){
                for(int j=left; j<=right; j++) count[j]++;
            }
            else {
                for(int j=1;j<=right; j++) count[j]++;
                for(int j=left;j<=m; j++) count[j]++;
            }
        }
        int res = 1;
        int curMax = 0;
        for(int i=1;i<=m; i++){
            if(count[i]>curMax) {
                res = i;
                curMax = count[i];
            }
        }
        return res;
    }
    public static void main(String[] args){
        System.out.println(findMostVisitedSmallest(3, new int[]{1,3,2,3}));
    }
}
