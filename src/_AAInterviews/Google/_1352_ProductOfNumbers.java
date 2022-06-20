package _AAInterviews.Google;
import java.util.*;
public class _1352_ProductOfNumbers {
    List<Integer> laskKProductList;
    public _1352_ProductOfNumbers() {
        laskKProductList = new LinkedList<>();
    }

    public void add(int num) {
        if(num==0) {
            laskKProductList.clear();
        }
        else {
            if (laskKProductList.size() > 0) {
                laskKProductList.add(laskKProductList.get(laskKProductList.size() - 1) * num);
            } else {
                laskKProductList.add(num);
            }
        }

    }

    public int getProduct(int k) {
        int size = laskKProductList.size();
        if(k>size) return 0;
        if(k==size) return laskKProductList.get(size-1);
        return laskKProductList.get(size-1)/laskKProductList.get(size-k-1);
    }
}
