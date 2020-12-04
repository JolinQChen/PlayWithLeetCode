package _AAInterviews.Amazon;

import sun.management.snmp.util.JvmContextFactory;

import java.util.*;
public class AmazonFreshPromotion {
    public static boolean winPrize(String[][] codeList, String[] shoppingCart) {
        int code_idx = 0;
        int cart_idx = 0;
        while (cart_idx<shoppingCart.length && code_idx < codeList.length) {

            // find first match
            if(!shoppingCart[cart_idx].equals(codeList[code_idx][0]) && !codeList[code_idx][0].equals("anything")) {
                while (cart_idx<shoppingCart.length && !shoppingCart[cart_idx].equals(codeList[code_idx][0]))
                cart_idx++;
            }
            // match this group
            else {
                int prev_cart = cart_idx;
                int tmp_idx = 0;
                boolean flag = true;
                while (cart_idx<shoppingCart.length && tmp_idx<codeList[code_idx].length) {
                    if(shoppingCart[cart_idx] != codeList[code_idx][tmp_idx] && !codeList[code_idx][tmp_idx].equals("anything")) {
                        flag = false;
                        break;
                    }
                    tmp_idx++;
                    cart_idx++;
                }
                if(flag) code_idx++;
                else {
                    cart_idx = prev_cart+1;
                }
            }

        }
        if(code_idx<codeList.length) return false;
        return true;
    }

    /** leetcode上某人的答案 */
    public static int winPrize2(String[][] codeList, String[] shoppingCart) {
        // checking corner cases
        if(codeList == null || codeList.length == 0)
            return 1;
        if(shoppingCart == null || shoppingCart.length == 0)
            return 0;

        int i = 0, j = 0;
        //int codeLen = codeList[i].length;
        while (i < codeList.length && j + codeList[i].length <= shoppingCart.length) {
            boolean match = true;
            for (int k = 0; k < codeList[i].length; k++) {
                if (!codeList[i][k].equals("anything") && !shoppingCart[j+k].equals(codeList[i][k])) {
                    match = false;
                    break;
                }
            }
            if (match) {
                j += codeList[i].length;
                i++;
            } else {
                j++;
            }
        }
        return (i == codeList.length) ? 1 : 0;
    }

 /** Leetcode另一个人的答案 */
    private static int solve(String[][] codeList, String[] shoppingCart) {
        if(codeList == null || codeList.length == 0)
            return 1;
        if(shoppingCart == null || shoppingCart.length == 0)
            return 0;
        int i=0,j=0;
        for(int k=0;k<shoppingCart.length;k++) {
            if(codeList[i][j].equals(shoppingCart[k]) || codeList[i][j].equals("anything")) {
                j++;
                if(j == codeList[i].length) {
                    i++;
                    j=0;
                }
                if(i == codeList.length)
                    return 1;
            }else {
                j = codeList[i][0].equals("anything") ? 1: 0;
            }
        }
        return 0;
    }





    public static void test(String[][] codeList, String[] shoppingCart, boolean expect) {
        System.out.println(winPrize(codeList, shoppingCart) == expect);
    }

    public static void main(String[] args) {
        // test cases
        String[][] codeList1 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart1 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList2 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart2 = {"banana", "orange", "banana", "apple", "apple"};
        String[][] codeList3 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart3 = {"apple", "banana", "apple", "banana", "orange", "banana"};
        String[][] codeList4 = { { "apple", "apple" }, { "apple", "apple", "banana" } };
        String[] shoppingCart4 = {"apple", "apple", "apple", "banana"};
        String[][] codeList5 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart5 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList6 = { { "apple", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart6 = {"apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList7= { { "anything", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart7 = {"orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList8 = {{"apple", "orange"}, {"orange", "banana", "orange"}};
        String[] shoppingCart8 = {"apple", "orange", "banana", "orange", "orange", "banana", "orange", "grape"};
        String[][] codeList9= { { "anything", "anything", "anything", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart9 = {"orange", "apple", "banana", "orange", "apple", "orange", "orange", "banana", "apple", "banana"};

        // test
        test(codeList1, shoppingCart1, true);
        test(codeList2, shoppingCart2, false);
        test(codeList3, shoppingCart3, false);
        test(codeList4, shoppingCart4, false);
        test(codeList5, shoppingCart5, true);
        test(codeList6, shoppingCart6, true);
        test(codeList7, shoppingCart7, true);
        test(codeList8, shoppingCart8, true);
        test(codeList9, shoppingCart9, true);
    }
}
