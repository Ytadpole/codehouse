package pers.yangs.advent.y2025.day3;

import pers.yangs.advent.y2025.MyUtil;

import java.util.List;

/**
 * @className: Day3
 * @Description：
 * @Author: 卡卡西
 * @Date: 2025/12/23
 **/
public class Day3 {

    public static void main(String[] args) {
        List<String> inputs = MyUtil.readLines("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2025/day3/input.pro");
        long res = 0;
        for (String input : inputs) {
            long power = part2(input);
            System.out.printf("input: %s, power:%s\n",input, power);
            res+=power;
        }
        System.out.println(res);
    }

    private static long part2(String input) {
        char[] chars = input.toCharArray();
        int[] idx = new int[12];
        idx[0] = 0;
        for(int i = 0; i < 12; i++) {
            for (int j = idx[i] + 1; j < chars.length - 11 + i; j++) {
                if(chars[j] > chars[idx[i]]) {
                    idx[i] = j;
                }
            }
            if(i < 11) {
                idx[i + 1] = idx[i] + 1;
            }
        }

        StringBuilder str = new StringBuilder();
        for (int index : idx) {
            str.append(chars[index]);
        }
        return Long.parseLong(str.toString());
    }

    private static long part1(String input) {
        char[] chars = input.toCharArray();
        int left = 0;
        for (int i = 1; i < chars.length-1; i++) {
            char c = chars[i];
            if(c > chars[left]) {
                left = i;
            }
        }
        int right = chars.length - 1;
        for (int i = chars.length - 2; i > left; i--) {
            if(chars[i] > chars[right]) {
                right = i;
            }
        }
        return Long.parseLong(chars[left]+""+chars[right]);
    }
}
