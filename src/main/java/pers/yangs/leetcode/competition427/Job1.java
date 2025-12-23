package pers.yangs.leetcode.competition427;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @className: Job1
 * @Description：
 * @Author: 卡卡西
 * @Date: 2024/12/6
 **/
public class Job1 {

    public int minOperations(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(num < k) {
                return -1;
            }
            set.add(num);
        }
        set.add(k);
        return set.size() - 1;
    }

    public static void main(String[] args) {
//        System.out.println(new Job1().findMinimumTime(new ArrayList<Integer>() {
//            {
//                add(3);
//                add(4);
//                add(1);
//            }
//        }, 1));

//        System.out.println(new Job1().findMinimumTime(new ArrayList<Integer>() {
//            {
//                add(2);
//                add(5);
//                add(4);
//            }
//        }, 2));
//
        System.out.println(new Job1().findMinimumTime(new ArrayList<Integer>() {
            {
                add(7);
                add(3);
                add(6);
                add(18);
                add(22);
                add(50);
            }
        }, 4));
    }

    public int findMinimumTime(List<Integer> strength, int K) {
        int x = 1;
        int time = 1;
        int curPower = 1;
        int count = 0;


        strength.sort((o1, o2) -> {
            return o2 - o1;
        });
        while (count < strength.size()) {
            String kill = "null";
            String stat = String.format("%5s|%5s|%5s|", time,  curPower ,  x);
            for (int i = 0; i < strength.size(); i++) {
                int needPower = strength.get(i);
                if (-1 == needPower) {
                    continue;
                }
                if (curPower >= needPower) {

                    Integer lastNeedPower = strength.get(i - 1);


                    strength.set(i, -1);
                    count++;
                    x += K;
                    curPower = 0;
                    kill = needPower+"";
                    break;
                }
            }
            String s = stat + "%5s|%5s|%s";
            System.out.printf(s, kill , x , strength.toString());
            System.out.println();
            if(count >= strength.size()) {
                break;
            }
            time++;
            curPower+=x;
        }
        return time;
    }
}
