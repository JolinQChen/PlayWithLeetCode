public class _1358_Number_of_Substrings_Containing_All_Three_Characters {
    public int numberOfSubstrings(String s)
    {
        int len = s.length();
        int left = 0;
        int right = 0;
        int count = 0;
        int[] abc_num = new int[3];
        char[] c = s.toCharArray();

        while(right<len)
        {
            while(right<len && !isComplete(abc_num))
            {
                abc_num[(int)(c[right]-'a')] ++;
                right++;
            }
            while(isComplete(abc_num))
            {
                count += len-(right-1);
                abc_num[(int)(c[left]-'a')] --;
                left++;
            }
        }
        return count;


    }

    private boolean isComplete(int[] abc_num)
    {
        for(int n:abc_num)
        {
            if(n==0) return false;
        }
        return true;
    }
}
