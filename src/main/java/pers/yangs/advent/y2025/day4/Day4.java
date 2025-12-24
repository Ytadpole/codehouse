package pers.yangs.advent.y2025.day4;

import pers.yangs.advent.y2025.MyUtil;

import java.util.List;

/**
 * @className: Day5
 * @Description：
 * @Author: 卡卡西
 * @Date: 2025/12/24
 **/
public class Day4 {
    static int dirs[][] = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},  {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public static void main(String[] args) {
        List<String> inputs = MyUtil.readLines("/Users/yangsong/code/java/codehourse" +
                "/src/main/java/pers/yangs/advent/y2025/day5/input.pro");
        char[][] chars = convert2Char(inputs);
//        long res = part1(chars);
        long res = part2(chars);
        System.out.println(res);
    }

    private static long part1(char[][] chars) {
        long res = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if('@' != chars[i][j]) {
                    continue;
                }
                long sum = getSum(chars, i,j);
                if(sum < 4) {
                    res++;
                    System.out.println("i:"+i+" j:"+j);
                }
            }
        }
        return res;
    }

    private static long part2(char[][] chars) {
        long res = 0;
        long loopCount = 0;
        do {
//            long res = 0;
            loopCount = 0;
            for (int i = 0; i < chars.length; i++) {
                for (int j = 0; j < chars[i].length; j++) {
                    if ('@' != chars[i][j]) {
                        continue;
                    }
                    long sum = getSum(chars, i, j);
                    if (sum < 4) {
                        chars[i][j] = '.';
                        res++;
                        loopCount++;
//                    System.out.println("i:"+i+" j:"+j);
                    }
                }
            }
        }while(loopCount > 0);
        return res;
    }

    private static long getSum(char[][] chars, int i, int j) {
        long count = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x < 0 || y < 0 || x >= chars.length || y>=chars[0].length) {
                continue;
            }
            if('@' == chars[x][y]) {
                count ++;
            }
        }
        return count;
    }

    private static char[][] convert2Char(List<String> inputs) {
        char[][] chars = new char[inputs.size()][];
        for (int i =0; i< inputs.size();i++){
            chars[i] = inputs.get(i).toCharArray();
        }
        return chars;
    }
}
