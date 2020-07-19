package _All;

/**
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation:
 * A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * */
public class _1011_Capacity_To_Ship_Packages_Within_D_Days {
    public int shipWithinDays(int[] weights, int D) {
        // array, binary search
        int sum = 0;
        int max = 0;
        for(int num:weights) {
            sum+=num;
            max = Math.max(max,num);
        }
        int left = Math.max(max,sum/D);
        int right = sum;
        while(left<right) {
            int mid = left+(right-left)/2;
            if(countDays(weights, mid)>D) left = mid+1;
            else right = mid;
        }
        return left;

    }
    private int countDays(int[] weight, int capacity) {
        int res = 0;
        int count = 0;
        for(int i=0; i<weight.length; i++){
            int num = weight[i];
            count += num;
            if(count==capacity) {
                res++;
                count=0;
            }
            else if(count>capacity) {
                count = num;
                res++;
            }
        }
        if(count>0) res++;
        return res;
    }
    public static void main(String[] args) {
        _1011_Capacity_To_Ship_Packages_Within_D_Days test = new _1011_Capacity_To_Ship_Packages_Within_D_Days();
        int[] weight = {1,2,3,1,1};
        System.out.println(test.countDays(weight,2));
    }
}
