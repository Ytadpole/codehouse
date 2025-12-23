package pers.yangs.leetcode.normal.t688;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: Solution
 * @Description：
 * @Author: 卡卡西
 * @Date: 2024/12/7
 **/
public class Solution {

    int[][] directs = new int[][] {
            { -2, 1 },
            { -1, 2 },
            { 1, 2 },
            { 2, 1 },
            { 2, -1 },
            { 1, -2 },
            { -1, -2 },
            { -2, -1 }
    };

    Map<String, Double> db = new HashMap<>();

    public static void main(String[] args) {
//        System.out.println(new Solution().knightProbability(3, 2, 0, 0));
        System.out.printf("%f", new Solution().knightProbability(8, 30, 6, 4));
    }

    public double knightProbability(int n, int k, int row, int column) {

        if(k == 0) {
            return 1;
        }

        double inc = stepIn( k, n, row, column);
        return inc;
    }

    public double stepIn(int k, int n, int row, int col) {
        if(row >= n || col >=n || row<0 || col<0) {
            return 0;
        }
        if(k <= 0) {
            return 1;
        }
        Double tmp = db.get(getKey(row, col, k));
        if(null != tmp) {
            return tmp;
        }
        double res = 0;
        for(int direct = 0; direct < directs.length; direct++) {
            int newRow = row + directs[direct][0];
            int newCol = col + directs[direct][1];

            res += stepIn(k - 1, n, newRow, newCol);
        }
        db.put(getKey(row, col, k), res/directs.length);
        return res/directs.length;
    }

    public String getKey(int row, int col, int k) {
        return row + "," + col +","+k;
    }
}
