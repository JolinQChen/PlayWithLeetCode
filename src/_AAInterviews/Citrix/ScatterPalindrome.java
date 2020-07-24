package _AAInterviews.Citrix;

/**
 * 首先，让我们考虑一下如何查找字符串是否可以是分散回文。
 * 让我们考虑字符串仅包含小写字符的情况。
 * 在以下情况下，字符串可以视为散布回文：
 * 当字符串的长度为偶数时：字符串中出现的所有字符必须出现偶数次。
 * 当字符串的长度为奇数时：字符串中只有一个字符出现奇数次，其他字符出现偶数次。
 *
 * 因此，要检查字符串是否可以是分散回文，我们只需要检查字符串中每个字符的出现次数即可。
 * 可以在O（n）中完成，其中n是字符串的长度。
 * 对于brute force的解决方案：生成所有子字符串的时间复杂度为O（n2）。
 * 为了检查子串是否是分散回文，我们需要另一个O（n）。
 * 因此，总时间复杂度为O（n3）。
 *
 * 我们可以在检查时降低O（n）因子，从而可以将总时间复杂度降低到O（n2）。
 * 为此，可以采用大小为n * 26的二维数组，其中n是字符串的长度。
 * 令该数组为A [n][26]。
 * 因此，A[i][j]存储从0到i的第j个字符*的出现总数。
 *
 * So for a string "abca", your array would look like：
 * 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 2 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 *
 * 现在，对于从索引l到r的任何子字符串，A[r] - A[l-1]都会为您提供子字符串中每个字符的出现情况。
 * 要检查这是否是散发回文，我们需要进行26次操作。
 * 因此，解的时间复杂度变为O（n^2 * 26），与O（n^2）渐近相同。
 *
 * Instead of storing the occurence of each character in an array we will store
 * that as an integer. If ith bit is '1' from lsb for say jth index it means that
 * ith character has occured odd number of times from 0 to jth index. If it is '0',
 * it signifies that ith character* has occured even number of times.
 *
 * Consider this example where input string is "abca"
 *
 * 所以我们的辅助数组将是
 * 1 3 7 6
 * 1->（0001）['a'出现一次]
 * 3->（0011）['a'和'b'发生过一次]
 * 7->（0111）['a'，'b'和'c'分别出现一次]
 * 6->（0110）[[a]出现两次，而'b'和'c'出现一次]
 * 因此，现在对于从索引l到r的任何子串A[r] xor A[l-1], 给出一个整数，如果该整数是0或2的幂，
 * 则该整数将包含在最终答案中（它全为0位或只有一个为1）
 * Pseudo-code is given below:
 *
 * input string = s
 * ans = 0
 * n = s.length
 *
 * for i=1:n
 *     A[i]=A[i-1]^(1<<(s[i-1]-97)) // 97是a
 *
 * for i=1:n
 *     for j=i;n
 *         x=A[j]^A[i-1]
 *         if (x&(x-1)) == 0    //if x is a power of 2 or not
 *             ans++;
 *         endif
 *     endfor
 * endfor
 * */

public class ScatterPalindrome {
    public static int scatterPalindrome(String str){
        int ans = 0;
        int n = str.length();
        int[] A = new int[n+1];
        for(int i=1; i<=n; i++){
            A[i] = A[i-1]^(1<<str.charAt(i-1)-'a');
            //A[i]表示从开始到"第"i位，在原先的index中是i-1
        }
        for(int i=1; i<=n; i++){
            for(int j=i; j<=n; j++){
                int x = A[i-1] ^ A[j];
                if((x&(x-1))==0) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        System.out.println(scatterPalindrome("abadaba"));
    }
}
