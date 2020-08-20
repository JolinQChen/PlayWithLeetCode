package _AAInterviews.MS;

public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n>0) {
            char tmp = (char) ('A' + (n-1)%26);
            n = (n-1)/26;
            sb.append(tmp);
        }
        sb.reverse();
        return sb.toString();
    }
}
