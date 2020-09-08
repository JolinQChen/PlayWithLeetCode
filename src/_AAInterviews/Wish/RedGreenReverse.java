package _AAInterviews.Wish;
/**
 * 红绿灯：
 * [红, 绿, 绿, 红, 红]. 翻转⼀个⼦区间(红变绿,绿变红),使得绿灯个数最⼤. 返回区间的起点和终点
 * 1 - red; -1 - green
 *
 * */
public class RedGreenReverse {
    public static int[] reverseInterval(int[] colors) {
        // 本质也是一个Maximum Subarray Problem
        int max_so_far = 0;
        int max_ending_here = 0;
        int start = 0, end = 0, next_start = 0;
        for(int i=0; i<colors.length; i++) {
            max_ending_here += colors[i];
            if(max_ending_here < 0) {
                if(i<colors.length-1) next_start = i+1;
                max_ending_here = 0;
            }
            else if(max_ending_here > max_so_far) {
                end = i;
                if(next_start>0) start = next_start;
                max_so_far = max_ending_here;
            }
        }
        return new int[]{start, end};
    }
}
