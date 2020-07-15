public class _168_Excel_Sheet_Column_Title {
    public String convertToTitle(int n)
    {
        StringBuilder res = new StringBuilder();
        while(n>0)
        {
            char tmp = (char)('A' + ((n-1)%26));
            n = (n-1) / 26;
            res.insert(0,tmp);
        }
        return res.toString();
    }
}
